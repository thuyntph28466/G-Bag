package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.CartDTO;
import fpoly.datn.ecommerce_website.entity.Cart;
import fpoly.datn.ecommerce_website.entity.ProductDetails;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.repository.ICartRepository;
import fpoly.datn.ecommerce_website.service.CartService;
import fpoly.datn.ecommerce_website.service.IUserService;
import fpoly.datn.ecommerce_website.service.serviceImpl.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ICartRepository iCartRepository;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private AuthenticationService authenticationService;

    ///GetOne
    @RequestMapping(value = "/cart/{cartId}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneCart(@PathVariable String cartId) {
        if (cartService.findById(cartId) != null) {
            return ResponseEntity.ok(cartService.findById(cartId));

        } else {
            return ResponseEntity.ok("Không tìm thấy ID !!!");
        }
    }

    @RequestMapping(value = "/cart/self", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestParam String productDetailId,Authentication authentication) {
        Users user = iUserService.findByAccount(authentication.getName());
        if (!iUserService.existsById(user.getUserId())) {
            return ResponseEntity.badRequest().body("Cần đăng nhập đúng tài khoản khách hàng");
        }
        Cart cart = new Cart();
        cart.setCartStatus(1);
        cart.setAmount(1);
        cart.setUser(user);
        ProductDetails productDetails= new ProductDetails();
        productDetails.setProductDetailId(productDetailId);
        cart.setProductDetails(productDetails);
        if (cartService.existsByCustomerIdAndProductDetailsProductDetailId(user.getUserId(), productDetails.getProductDetailId())) {
            return ResponseEntity.badRequest().body("Sản phẩm đã có trong giỏ hàng");
        }
        return new ResponseEntity<>(this.cartService.save(cart), HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/self", method = RequestMethod.GET)
    public ResponseEntity<?> getCartDetails( Authentication authentication) {
        Users user = iUserService.findByAccount(authentication.getName());
        if (!iUserService.existsById(user.getUserId())) {
            return ResponseEntity.badRequest().body("Cần đăng nhập đúng tài khoản khách hàng");
        }
        List<Cart> carts = cartService.getAllCartsByCustomerId(user.getUserId());
        for (Cart cart:carts) {
            cart.setUser(null);
        }
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/carts/{customerId}")
    public ResponseEntity<?> getOneCustomer(@PathVariable String customerId) {
        if (cartService.findAll(customerId) != null) {
            return ResponseEntity.ok(cartService.findAll(customerId));

        } else {
            return ResponseEntity.ok("Không tìm thấy ID !!!");
        }
    }



    //PhanTrang
    @RequestMapping(value = "/cart/pagination", method = RequestMethod.GET)
    public ResponseEntity<?> phanTrang(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        return ResponseEntity.ok(cartService.findAllPhanTrang(page));
    }


    //
    //Add
    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ResponseEntity<?> add2(@RequestBody CartDTO cartDTO) {
        return new ResponseEntity<>(this.cartService.save(cartDTO), HttpStatus.OK);
    }

    //update
    @RequestMapping(value = "/cart", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody @Valid CartDTO cartDTO, @RequestParam String id) {

        if (cartService.findById(id) != null) {
            return ResponseEntity.ok(cartService.update(cartDTO, id));

        } else {
            return ResponseEntity.ok("ID cần update không tồn tại, vui lòng kiểm tra lại ID !!");
        }

    }

    //delete
    @RequestMapping(value = "/cart", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String id) {
        if (cartService.delete(id)) {
            return ResponseEntity.ok("Xóa thành công!!");
        } else {
            return ResponseEntity.ok("id không tồn tại để xóa!!");
        }
    }


}
