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

import java.util.UUID;

@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Colors {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "color_id")
    private String colorId;


    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "color_name",columnDefinition = "NVARCHAR(255)")
    private String colorName;

    @Column(name = "color_status")
    private Integer colorStatus;
}
