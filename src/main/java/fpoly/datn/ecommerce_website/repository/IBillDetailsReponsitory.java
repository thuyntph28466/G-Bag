package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.entity.BillDetails;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface IBillDetailsReponsitory extends JpaRepository<BillDetails,String> {
}
