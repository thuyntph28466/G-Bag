package fpoly.datn.ecommerce_website.service.serviceImpl;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import fpoly.datn.ecommerce_website.dto.LoginRequest;
import fpoly.datn.ecommerce_website.dto.LoginResponse;
import fpoly.datn.ecommerce_website.dto.UserDTO;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.repository.IUserRepository;
import fpoly.datn.ecommerce_website.service.IUserService;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public Users findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public boolean existsById(String s) {
        return userRepository.existsById(s);
    }


     public boolean checkPassword(String rawPassword, String encodedPassword) {
         return passwordEncoder.matches(rawPassword, encodedPassword);
     }
    @PostAuthorize("returnObject.account == authentication.name")
    public Users myInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByAccount(name);
        return user;
    }
    @PostAuthorize("returnObject.account == authentication.name")
    public Users updateProfile(UserDTO userDTO) {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByAccount(name);
        user.setFullName(userDTO.getFullName());
        user.setGender(userDTO.getGender());
        user.setPhoneNumber(String.valueOf(userDTO.getPhoneNumber()));
        user.setAddress(String.valueOf(userDTO.getAddress()));
        Users userSeved = userRepository.save(user);
        return user;
    }

    public boolean changePassword(String password, String newPassword) {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByAccount(name);
          if (isPasswordMatch(password, user.getPassword())) {
              user.setPassword(passwordEncoder.encode(newPassword));
              userRepository.save(user);
              return true;
          }
        return false;

    }


    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Users user = userRepository.findByAccount(loginRequest.getAccount());

        if (user != null &&checkPassword(loginRequest.getPassword(), user.getPassword()) && user.getUserStatus()==1) {
            // Tạo token hoặc phản hồi thông tin đăng nhập (giả sử có tạo token)
            String token = generateToken(user); // Hàm này để tạo JWT hoặc token khác
            return new LoginResponse(token, user.getRoleName());
        } else {
            throw new RuntimeException("Invalid credentials or user is inactive");
        }
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
                .claim("scope","ROLE_"+ user.getRoleName().toUpperCase())
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

    @Override
    public List<Users> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Users findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Users save(Users entity) {
        return userRepository.save(entity);
    }

    @Override
    public Users update(Users entity) {
        return userRepository.save(entity);    }

    @Override
    public String delete(String id) {
        userRepository.deleteById(id);
        return "Delete successfully";
    }

    @Override
    public Users findByPhoneNumberEquals(String phoneNumber) {
        return userRepository.findByPhoneNumberEquals(phoneNumber);
    }

}