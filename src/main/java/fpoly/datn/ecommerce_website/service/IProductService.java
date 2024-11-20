package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.ProductDTO;
import fpoly.datn.ecommerce_website.dto.Product_BrandDTO;
import fpoly.datn.ecommerce_website.entity.Products;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    String GetproductStatusString(int productStatus);


    Page<ProductDTO> findAllPagination(
            int pageNum,
            int PageSize,
            String productName,
            String productCode,
            String brandName,
            Integer productStatus,
            List<String> sortList,
            String sortOrder
    );

    List<ProductDTO> findAll();

    List<Product_BrandDTO> test();

    Products findById(String id);

    Products save(Products entity);

    Products update(Products entity);

    Products updateProductStatus(String productID, int status);

    String delete(String id);

    List<Products> searchByName(String name);
}

