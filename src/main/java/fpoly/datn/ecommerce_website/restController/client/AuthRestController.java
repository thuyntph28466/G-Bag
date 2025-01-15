package fpoly.datn.ecommerce_website.restController.client;


import fpoly.datn.ecommerce_website.dto.LoginRequest;
import fpoly.datn.ecommerce_website.dto.LoginResponse;
import fpoly.datn.ecommerce_website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {


        return userService.authenticateUser(loginRequest);
    }
}