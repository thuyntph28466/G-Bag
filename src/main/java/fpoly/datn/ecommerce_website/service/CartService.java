package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.CartDTO;
import fpoly.datn.ecommerce_website.entity.Cart;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {


    Page<Cart> findAllPhanTrang(Integer page);

    List<Cart> findAll(String cartId);

    Cart findById(String id);

    Cart save(CartDTO cartDTO);

    Cart update(CartDTO cartDTO, String id);

    Boolean delete(String id);

    List<Cart> searchByName(String name);
}
