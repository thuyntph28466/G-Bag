package fpoly.datn.ecommerce_website.dto;

import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.entity.Colors;
import fpoly.datn.ecommerce_website.entity.Compartments;
import fpoly.datn.ecommerce_website.entity.Images;
import fpoly.datn.ecommerce_website.entity.Materials;
import fpoly.datn.ecommerce_website.entity.Producers;
import fpoly.datn.ecommerce_website.entity.Products;
import fpoly.datn.ecommerce_website.entity.Sizes;
import fpoly.datn.ecommerce_website.entity.Types;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ProductDetailDTO {
    private String productDetailId;
    private BigDecimal importPrice;
    private BigDecimal retailPrice;
    private Integer productDetailAmount;
    private String productDetailDescribe;
    private Integer productDetailStatus;
    private Products product;
    private Colors color;
    private List<Images> images;

    private Integer quantity;



}
