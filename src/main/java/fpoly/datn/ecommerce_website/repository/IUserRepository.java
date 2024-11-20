package fpoly.datn.ecommerce_website.repository;

import fpoly.datn.ecommerce_website.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, String> {
    Users findByAccountAndPassword(String account, String password);
}
