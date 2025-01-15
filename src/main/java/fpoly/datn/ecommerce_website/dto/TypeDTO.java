package fpoly.datn.ecommerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDTO {
    private String typeId;

    @NotBlank
    private String typeCode;

    @NotBlank
    private String typeName;

    private Integer typeStatus;
}
