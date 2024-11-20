package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.LoginRequest;
import fpoly.datn.ecommerce_website.dto.LoginResponse;

public interface IUserService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
}
