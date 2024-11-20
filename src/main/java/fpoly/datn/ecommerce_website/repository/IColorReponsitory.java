package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IColorReponsitory extends JpaRepository<Colors, String> {
}
