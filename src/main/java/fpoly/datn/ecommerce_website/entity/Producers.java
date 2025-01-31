package fpoly.datn.ecommerce_website.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "producers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Producers {
    @Id
    @Column(name = "producer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String producerId;

    @Column(name = "producer_code")
    private String producerCode;

    @Column(name = "producer_name",columnDefinition = "NVARCHAR(255)")
    private String producerName;

    @Column(name = "producer_status")
    private Integer producerStatus;
}
