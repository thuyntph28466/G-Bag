package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.CartDTO;
import fpoly.datn.ecommerce_website.entity.Cart;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {


    Page<Cart> findAllPhanTrang(Integer page);

    List<Cart> findAll(String cartId);

    boolean existsByCustomerIdAndProductDetailsProductDetailId(String customerId, String productDetailId);

    List<Cart> getAllCartsByCustomerId(String userId);

    Cart findById(String id);

    Cart save(CartDTO cartDTO);

    Cart save(Cart cart);

    Cart update(CartDTO cartDTO, String id);

    Boolean delete(String id);

    List<Cart> searchByName(String name);
}
