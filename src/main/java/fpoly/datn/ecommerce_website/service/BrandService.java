package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
        List<Brands> findAll();


        Brands findById(String id);

       Brands save(Brands entity);

        Brands update(Brands entity);

        String delete(String id);




    }
