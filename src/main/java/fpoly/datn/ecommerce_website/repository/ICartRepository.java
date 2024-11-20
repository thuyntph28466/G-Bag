package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart, String> {

    @Query("select c from Cart c  " +
            "where c.user.userId=:userId")
    List<Cart> getAllCartsByCustomerId(@Param("userId") String userId);
}
