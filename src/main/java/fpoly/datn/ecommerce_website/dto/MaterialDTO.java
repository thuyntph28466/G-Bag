package fpoly.datn.ecommerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDTO
{
    private String materialId;

    @NotBlank(message = "Không để trống code")
    private String materialCode;

    @NotBlank(message = "Không để trống name")
    private String materialName;

    private Integer materialStatus;
}
