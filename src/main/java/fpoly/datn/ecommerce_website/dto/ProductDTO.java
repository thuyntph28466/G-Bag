package fpoly.datn.ecommerce_website.dto;


import fpoly.datn.ecommerce_website.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class ProductDTO {

    private String productId;

    private String productCode;

    private String productName;

    private Integer productStatus;

    private Brands brand;

    private Types type;
    private Materials material;
    private Sizes size;
    private Compartments compartment;
    private BuckleTypes buckleType;
    private Producers producer;
    
    private List<ProductDetails> productDetails;
}