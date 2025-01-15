package fpoly.datn.ecommerce_website.service.serviceImpl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import fpoly.datn.ecommerce_website.dto.AuthenticationRequest;
import fpoly.datn.ecommerce_website.dto.AuthenticationResponse;
import fpoly.datn.ecommerce_website.dto.IntrospectRequest;
import fpoly.datn.ecommerce_website.dto.IntrospectResponse;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {
    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    IUserRepository userRepository;

    public AuthenticationResponse authentication(AuthenticationRequest request) throws Exception {

        var user = userRepository
                .findByAccount(request.getAccount());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


        if (user == null) {
            throw new Exception("Thông tin không chính sác");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new Exception("Thông tin không chính sác");

        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }



public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
    var token = request.getToken();
    boolean isValid = true;

    try {
        verifyToken(token, false);
    } catch (Exception e) {
        isValid = false;
    }

    return IntrospectResponse.builder().valid(isValid).build();
}

    private String generateToken(Users user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getAccount())
                .issuer("datn.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", "ROLE_"+user.getRoleName().toUpperCase())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.info("không tạo được token");
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }




    private SignedJWT verifyToken(String token, boolean isRefresh) throws Exception {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new Exception("Token quá hạn");

        return signedJWT;
    }
}