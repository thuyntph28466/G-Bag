package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.dto.SaleOfRequest;
import fpoly.datn.ecommerce_website.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillsReponsitory extends JpaRepository<Bills,String> {
}
