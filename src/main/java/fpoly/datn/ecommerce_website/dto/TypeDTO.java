package fpoly.datn.ecommerce_website.dto;

import jakarta.validation.constraints.NotBlank;

public class TypeDTO {
    private String typeId;

    @NotBlank
    private String typeCode;

    @NotBlank
    private String typeName;

    private Integer typeStatus;
}
