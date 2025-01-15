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
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Brands {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "brand_id")
    private String brandId;


    @Column(name = "brand_code")
    private String brandCode;

    @Column(name = "brand_name",columnDefinition = "NVARCHAR(255)")
    private String brandName;

    @Column(name = "brand_status")
    private Integer brandStatus;
}

