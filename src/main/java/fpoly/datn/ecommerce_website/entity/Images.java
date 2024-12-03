package fpoly.datn.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;


@Entity
@Table(name = "images"  )
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Images {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String imageId;


    @Column(name = "isPrimary")
    private Boolean isPrimary;
    @JsonIgnore
    @Column(name = "data")
    private Blob data;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    @JsonBackReference
    private ProductDetails productDetails;
}
