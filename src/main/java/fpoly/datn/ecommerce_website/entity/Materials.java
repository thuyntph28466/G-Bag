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
@Table(name = "materials")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Materials {
    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String materialId;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_name",columnDefinition = "NVARCHAR(255)")
    private String materialName;

    @Column(name = "material_status")
    private Integer materialStatus;
}
