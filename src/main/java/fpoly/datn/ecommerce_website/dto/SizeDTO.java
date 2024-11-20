package fpoly.datn.ecommerce_website.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
public class SizeDTO {
    private String sizeId;
    private String sizeCode;
    private String sizeName;
    private String sizeLength;
    private String sizeWidth;
    private String sizeHeight;
    private Integer sizeStatus;

}
