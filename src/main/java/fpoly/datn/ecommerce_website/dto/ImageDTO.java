package fpoly.datn.ecommerce_website.dto;

import fpoly.datn.ecommerce_website.entity.Products_ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ImageDTO {
    private String imageId;
    private String imgCode;
    private String imgName;
    private String imgUrl;
    private Boolean isPrimary;
    private Products_ProductDetails products_productDetails;
}
