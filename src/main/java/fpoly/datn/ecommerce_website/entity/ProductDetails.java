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

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_detail_id")
    private String productDetailId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productDetails")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors color;


    @Column(name = "import_price")
    private BigDecimal importPrice;

    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    @Column(name = "amount")
    private Integer productDetailAmount;

    @Column(name = "describe",columnDefinition = "NVARCHAR(MAX)")
    private String productDetailDescribe;

    @Column(name = "product_detail_status")
    private Integer productDetailStatus;


    @OneToMany(mappedBy = "productDetails", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Images> images;



}
