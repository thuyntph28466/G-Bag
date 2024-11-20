package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.dto.CartDTO;
import fpoly.datn.ecommerce_website.entity.Cart;
import fpoly.datn.ecommerce_website.repository.ICartRepository;

import fpoly.datn.ecommerce_website.repository.IUserRepository;

import fpoly.datn.ecommerce_website.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ICartRepository iCartRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Cart> findAll(String cartId) {
            return iCartRepository.getAllCartsByCustomerId(cartId);
    }


    @Override
    public Page<Cart> findAllPhanTrang(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return iCartRepository.findAll(pageable);
    }



    @Override
    public Cart findById(String id) {
        return iCartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart save(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);

        return iCartRepository.save(cart);

    }

    @Override
    public Cart update(CartDTO cartDTO, String id) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        cart.setCartId(id);
        return iCartRepository.save(cart);
    }


    @Override
    public List<Cart> searchByName(String name) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Cart> optional = iCartRepository.findById(id);
        if (optional.isPresent()) {
            Cart kh = optional.get();
            iCartRepository.delete(kh);
            return true;
        } else {
            return false;
        }
    }
}
