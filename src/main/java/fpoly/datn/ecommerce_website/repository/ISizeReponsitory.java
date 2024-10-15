package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeReponsitory extends JpaRepository<Sizes,String> {
}
