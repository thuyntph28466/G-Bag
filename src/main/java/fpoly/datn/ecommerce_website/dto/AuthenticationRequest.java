package fpoly.datn.ecommerce_website.dto;

import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @Size(min = 5, max = 20, message = "UserName 5-20 kí tự")
    String account;

    @Size(min = 5, max = 20, message = "Password 5-20 kí tự")
    String password;
}