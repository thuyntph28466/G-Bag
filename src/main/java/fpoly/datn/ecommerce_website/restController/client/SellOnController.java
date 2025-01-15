package fpoly.datn.ecommerce_website.restController.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fpoly.datn.ecommerce_website.dto.SellOnRequest;
import fpoly.datn.ecommerce_website.dto.TransportationFeeDTO;
import fpoly.datn.ecommerce_website.entity.*;
import fpoly.datn.ecommerce_website.service.*;
import fpoly.datn.ecommerce_website.service.serviceImpl.BillDetailService;
import fpoly.datn.ecommerce_website.service.serviceImpl.BillService;
import fpoly.datn.ecommerce_website.service.serviceImpl.GiaoHangTietKiemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/sellon")
@RestController
public class SellOnController {
    @Autowired
    IBillService iBillServices;
    @Autowired
    IProductDetalisService productDetailService;
    @Autowired
    IBillDetailService iBillDetailService;
    @Autowired
    IUserService iUserService;
    @Autowired
    private GiaoHangTietKiemServiceImpl giaoHangTietKiemServiceImpl;
    @Autowired
    private BillService billService;
    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private CartService cartService;

    @PostMapping("/calculate-money")
    public ResponseEntity<?> calculateMoney(@RequestBody List<Cart> carts){
        Double sum =0D;
        if (carts.size()>0){
            for (Cart cart : carts){
                ProductDetails productDetail =  productDetailService.findById(cart.getProductDetails().getProductDetailId());
                if (cart.getAmount()==null){
                    return ResponseEntity.badRequest().body("Thông tin sản phẩm hoặc số lượng bị thiếu");
                }
                if (productDetail == null){
                    return ResponseEntity.badRequest().body("Sản phẩm "+cart.getProductDetails().getProductDetailId()+" không tồn tại hoặc đã ngừng kinh doanh");
                }
                sum+=cart.getAmount()*productDetail.getRetailPrice().doubleValue();
            }

        }
        return ResponseEntity.ok(sum);

    }


    @PostMapping ()
    public ResponseEntity<?> add(@RequestBody() SellOnRequest sellOnRequest, Authentication authentication) {

        if (sellOnRequest.getPaymentType()==null||sellOnRequest.getAddress()==null||sellOnRequest.getCityId()==null||sellOnRequest.getDistrictId()==null||sellOnRequest.getWardCode()==null||sellOnRequest.getNote()==null||sellOnRequest.getPhoneNumber()==null||sellOnRequest.getCarts()==null) {
            return ResponseEntity.badRequest().body("Thông tin Không đầy đủ");
        }
        List<Cart>carts = sellOnRequest.getCarts();
        if (carts.size()<=0){
            return ResponseEntity.badRequest().body("Đơn hàng trống !");
        }

        List<BillDetails> billDetails = new ArrayList<>();
        Double sum =0D;
        Integer sumQ =0;
        for (Cart cart : carts){
            if (cart.getProductDetails().getProductDetailId()==null||cart.getAmount()==null){
                return ResponseEntity.badRequest().body("Thông tin sản phẩm hoặc số lượng bị thiếu");
            }
            ProductDetails productDetail =  productDetailService.findById(cart.getProductDetails().getProductDetailId());
            if (productDetail == null||productDetail.getProductDetailStatus().equals(0)){
                return ResponseEntity.badRequest().body("Sản phẩm "+cart.getProductDetails().getProductDetailId()+" không tồn tại hoặc đã ngừng kinh doanh");
            }
            if (productDetail.getProductDetailAmount()<cart.getAmount()){
                return ResponseEntity.badRequest().body("Sản phẩm "+cart.getProductDetails().getProductDetailId()+" không đủ số lượng");
            }
            if (cart.getAmount()<=0){
                return ResponseEntity.badRequest().body("Sản phẩm "+cart.getProductDetails().getProductDetailId()+" số lượng phải lớn hơn 0");
            }
            BillDetails billDetail = new BillDetails();
            billDetail.setProductDetails(productDetail);
            billDetail.setPrice(productDetail.getRetailPrice().doubleValue());
            billDetail.setAmount(cart.getAmount());
            billDetail.setBillDetailStatus(1);
            billDetails.add(billDetail);

            sum+=cart.getAmount()*productDetail.getRetailPrice().doubleValue();
            sumQ+=cart.getAmount();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Double shipFee = 0D;
        TransportationFeeDTO transportationFeeDTO = new TransportationFeeDTO();
        transportationFeeDTO.setInsuranceValue(sum);
        transportationFeeDTO.setQuantity(sumQ);
        transportationFeeDTO.setToDistrictId(sellOnRequest.getDistrictId());
        transportationFeeDTO.setToWardCode(sellOnRequest.getWardCode());
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(giaoHangTietKiemServiceImpl.getFee(transportationFeeDTO));
            shipFee = jsonNode.get("data").get("total").asDouble();

        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Không tính dược tiền ship");
        }



        Bills newBill = new Bills();
        Users user = iUserService.findByAccount(authentication.getName());
        newBill.setBillCreateDate(new Date());
        newBill.setShipPrice(new BigDecimal(shipFee));
        newBill.setBillTotalPrice(new BigDecimal(sum+shipFee));
        newBill.setCustomer(user);

        newBill.setBillingAddress(sellOnRequest.getAddress());

        newBill.setOrderPhone(sellOnRequest.getPhoneNumber());

        newBill.setBillNote((sellOnRequest.getNote()));

        if (sellOnRequest.getPaymentType().equals(-2)){

            newBill.setPaymentMethod(sellOnRequest.getPaymentType());
            newBill.setBillStatus(1);

        }else {
            return ResponseEntity.badRequest().body("Phương thức thanh toán không đúng !");
        }

        Bills newBillSaved = null;
        try {
            newBillSaved = billService.save(newBill);
            for (BillDetails billDetail : billDetails){
                billDetail.setBills(newBillSaved);
            }

            billDetailService.saveAll(billDetails);
            for (Cart cart : carts){
                cartService.delete(cart.getCartId());
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lỗi , Kiểm tra lại hóa đơn : ");

        }
        return ResponseEntity.ok(newBillSaved.getBillId().toString());

    }







}
