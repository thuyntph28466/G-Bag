package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.dto.SaleOfRequest;
import fpoly.datn.ecommerce_website.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IBillsReponsitory extends JpaRepository<Bills,String> {

    @Query("select b from  Bills  b where b.customer.userId=:customerId and (:type is null or b.billStatus= :type) order by  b.billCreateDate desc ")
    public List<Bills> findAllByCustomerUserIdAndBillStatus(String customerId, Integer type);

    @Query("select b from  Bills  b where  (:type is null or b.billStatus= :type) order by  b.billCreateDate desc ")
    public List<Bills> findAllByBillStatus(Integer type);

}
