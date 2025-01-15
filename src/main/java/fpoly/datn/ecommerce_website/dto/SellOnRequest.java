package fpoly.datn.ecommerce_website.dto;

import fpoly.datn.ecommerce_website.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellOnRequest {
    private List<Cart> carts;
    private String phoneNumber;
    private String address;
    private String cityId;
    private String districtId;
    private String wardCode;
    private String note;
    private Integer paymentType;
}
