package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.ProductDetailDTO;
import fpoly.datn.ecommerce_website.dto.SaleOfRequest;
import fpoly.datn.ecommerce_website.dto.SizeDTO;
import fpoly.datn.ecommerce_website.entity.BillDetails;
import fpoly.datn.ecommerce_website.entity.Bills;
import fpoly.datn.ecommerce_website.entity.ProductDetails;
import fpoly.datn.ecommerce_website.entity.Sizes;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.service.IBillDetailService;
import fpoly.datn.ecommerce_website.service.IBillService;
import fpoly.datn.ecommerce_website.service.IProductDetalisService;
import fpoly.datn.ecommerce_website.service.IUserService;
import fpoly.datn.ecommerce_website.service.serviceImpl.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api")
@RestController
public class SaleOfRequestControler {
    @Autowired
    IBillService iBillServices;
    @Autowired
    IProductDetalisService productDetailService;
    @Autowired
    IBillDetailService iBillDetailService;
    @Autowired
    IUserService iUserService;
    @RequestMapping(value = "/saleof", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SaleOfRequest saleof ) {
        if(saleof.getSelectedProducts().size()<1){
            return ResponseEntity.badRequest().body("Chưa chọn sản phẩm nào");
        }

              Double total= 0D;

        for (ProductDetailDTO item:saleof.getSelectedProducts()){

            ProductDetails productDetails = productDetailService.findById(item.getProductDetailId());
total = total + productDetails.getRetailPrice().doubleValue() * item.getQuantity();
     if(productDetails.getProductDetailAmount()<item.getQuantity()){

         return ResponseEntity.badRequest().body("Không đủ số lượng");

     }
        }


        Bills bill = new Bills();
        bill.setBillStatus(10);
        bill.setBillNote(saleof.getNote());
        if (saleof.getUserSelected().getUserId()==null){
            bill.setCustomer(null);
        }else{
            Users user = iUserService.findById(saleof.getUserSelected().getUserId());
            if (user==null){

            }else {
                bill.setCustomer(user);
            }

        }
        bill.setBillTotalPrice(new BigDecimal(total));
        bill.setBillCreateDate(new Date());
        Bills billsaved = iBillServices.save(bill);


        for (ProductDetailDTO item:saleof.getSelectedProducts()){
            BillDetails billDetail = new BillDetails();
            ProductDetails productDetails = productDetailService.findById(item.getProductDetailId());
            billDetail.setProductDetails(productDetails);
            billDetail.setAmount(item.getQuantity());
            billDetail.setPrice(productDetails.getRetailPrice().doubleValue());
            billDetail.setBillDetailStatus(1);

            billDetail.setBills(billsaved);
            iBillDetailService.save(billDetail);
            productDetails.setProductDetailAmount(productDetails.getProductDetailAmount()-billDetail.getAmount());
            productDetailService.save(productDetails);
        }
     return ResponseEntity.ok(iBillServices.findById(billsaved.getBillId()).get());
    }
}
