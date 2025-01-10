package fpoly.datn.ecommerce_website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleOfRequest {
   String note;
   List <ProductDetailDTO> selectedProducts;
   UserDTO userSelected;
}
