package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.LoginRequest;
import fpoly.datn.ecommerce_website.dto.LoginResponse;
import fpoly.datn.ecommerce_website.entity.Users;

import java.util.List;

public interface IUserService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
    List<Users> findAll();


    Users findById(String id);

    Users save(Users entity);

    Users update(Users entity);

    String delete(String id);

    Users findByPhoneNumberEquals(String phoneNumber);
}
