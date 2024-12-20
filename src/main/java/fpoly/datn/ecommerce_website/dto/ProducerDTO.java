package fpoly.datn.ecommerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProducerDTO {
    private UUID producerId;

    @NotBlank
    private String producerCode;

    @NotBlank
    private String producerName;

    private Integer producerStatus;
}
