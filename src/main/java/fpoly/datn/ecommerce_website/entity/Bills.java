package fpoly.datn.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "bills", schema = "dbo", catalog = "FashionBagsEcommerceDB")

public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "bill_id")
    private String billId;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "voucher_id")
    private Vouchers voucher;

    @OneToMany(mappedBy = "bills", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("bills")// Ngăn việc serialize product trong ProductDetail
    private List<BillDetails> details = new ArrayList<>();
    @Column(name = "bill_code")
    private String billCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bill_create_date")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billCreateDate;
    @Temporal(TemporalType.TIMESTAMP)

    @Column(name = "bill_date_payment")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billDatePayment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bill_ship_date")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billShipDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bill_receiver_date")
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billReceiverDate;


    @Column(name = "bill_total_price")
    private BigDecimal billTotalPrice;

    @Column(name = "product_amount")
    private Integer productAmount;

    @Column(name = "bill_price_after_voucher")
    private Double billPriceAfterVoucher;


    @Column(name = "shipping_address",columnDefinition = "NVARCHAR(MAX)")
    private String shippingAddress;

    @Column(name = "billing_adress",columnDefinition = "NVARCHAR(MAX)")
    private String billingAddress;

    @Column(name = "receiver_name",columnDefinition = "NVARCHAR(MAX)")
    private String receiverName;

    @Column(name = "ship_price")
    private BigDecimal shipPrice;

    @Column(name = "order_email")
    private String orderEmail;

    @Column(name = "order_phone")
    private String orderPhone;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @Column(name = "bill_note",columnDefinition = "NVARCHAR(MAX)")
    private String billNote;

    @Column(name = "bill_status")
    private Integer billStatus;

    @Column(name = "bill_reduced_price")
    private BigDecimal billReducedPrice;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "user_id")
    private Users staff;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Users customer;
}
