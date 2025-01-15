package fpoly.datn.ecommerce_website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportationFeeDTO {

    public static final Integer heightProduct = 20;

    public static final Integer lengthProduct = 40;

    public static final Integer weightProduct = 500;

    public static final Integer widthProduct = 20;

    private  String toDistrictId;

    private String toWardCode;

    private Double insuranceValue;

    private Integer quantity;

}