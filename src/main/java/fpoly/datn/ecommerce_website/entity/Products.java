package fpoly.datn.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Products {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name",columnDefinition = "NVARCHAR(MAX)")
    private String productName;

    @Column(name = "product_status")
    private int productStatus;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brand;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Types type;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Materials material;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes size;

    @ManyToOne
    @JoinColumn(name = "compartment_id")
    private Compartments compartment;

    @ManyToOne
    @JoinColumn(name = "buckle_type_id")
    private BuckleTypes buckleType;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producers producer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    @JsonManagedReference

    private List<ProductDetails> productDetails;

}
