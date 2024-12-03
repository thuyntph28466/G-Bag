package fpoly.datn.ecommerce_website.service.serviceImpl;


import fpoly.datn.ecommerce_website.dto.LoginRequest;
import fpoly.datn.ecommerce_website.dto.LoginResponse;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.repository.IUserRepository;
import fpoly.datn.ecommerce_website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Users user = userRepository.findByAccountAndPassword(
                loginRequest.getAccount(),
                loginRequest.getPassword()
        );

        if (user != null && "active".equals(user.getUserStatus())) {
            // Tạo token hoặc phản hồi thông tin đăng nhập (giả sử có tạo token)
            String token = generateToken(user); // Hàm này để tạo JWT hoặc token khác
            return new LoginResponse(token, user.getRoleName());
        } else {
            throw new RuntimeException("Invalid credentials or user is inactive");
        }
    }

    private String generateToken(Users user) {
        // Logic tạo token (JWT, hoặc token đơn giản)
        return "dummyToken";
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
}