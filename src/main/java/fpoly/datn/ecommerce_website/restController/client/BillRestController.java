package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.entity.BillDetails;
import fpoly.datn.ecommerce_website.entity.Bills;
import fpoly.datn.ecommerce_website.entity.ProductDetails;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.service.IUserService;
import fpoly.datn.ecommerce_website.service.serviceImpl.BillDetailService;
import fpoly.datn.ecommerce_website.service.serviceImpl.BillService;
import fpoly.datn.ecommerce_website.service.serviceImpl.ProductDetailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/api")

public class BillRestController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    ProductDetailServiceImpl productDetailService;
    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
    ) {
        List<Bills> bills = billService.findAllByBillStatus(null);
        return new ResponseEntity<>
                (bills, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bill/sellon")
    @ResponseBody
    public List<Bills> getBillSellOn(

            @RequestParam(value = "search", required = false) Optional<String> searchValue,
            @RequestParam(value = "type", required = false) Optional<Integer> type,
            HttpServletRequest request, Authentication authentication
    ) {
        Users user = iUserService.findByAccount(authentication.getName());
        Integer typeInt = type.orElse(-1);
        return billService.findAllByCustomerUserIdAndBillStatus(user.getUserId(), typeInt == -1 ? null : typeInt);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bill/cancel-bill-admin/{id}")
    public ResponseEntity<?> huyBillAdmin(@PathVariable String id, Authentication authentication) {
        if (billService.existsById(id)) {
            Bills bill = billService.findById(id).get();


                   if (bill.getBillStatus() == 1) {

                    bill.setBillStatus(0);
                    billService.save(bill);
                    return ResponseEntity.ok("Hủy đơn thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể hủy khi đơn Chờ xử lí  !");
                }


        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/bill/cancel-bill/{id}")
    public ResponseEntity<?> huyBill(@PathVariable String id, Authentication authentication) {
        if (billService.existsById(id)) {
            Bills bill = billService.findById(id).get();
            Users customer = iUserService.findByAccount(authentication.getName());
            if (customer == null) {
                return ResponseEntity.badRequest().body("Không lấy được thông tin đăng nhập");
            }
            if (customer.getUserId().equals(bill.getCustomer().getUserId())) {
                if (bill.getBillStatus() == 1) {

                    bill.setBillStatus(0);
                    billService.save(bill);
                    return ResponseEntity.ok("Hủy đơn thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể hủy khi đơn Chờ xử lí hoặc chờ thanh toán !");
                }
            } else {
                return ResponseEntity.badRequest().body("Bạn không có quyền hủy đơn này");
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bill/nhan-hang-admin/{id}")
    public ResponseEntity nhanHangAdmin(@PathVariable String id,Authentication authentication) {
        if (billService.existsById(id)){
            Bills bill = billService.findById(id).get();
                if (bill.getBillStatus()==5){
                    bill.setBillReceiverDate(new Date());
                    bill.setBillStatus(6);
                    billService.save(bill);
                    return ResponseEntity.ok("Đã nhận hàng thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể nhận đơn đang giao hàng !");
                }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/bill/nhan-hang/{id}")
    public ResponseEntity nhanHang(@PathVariable String id,Authentication authentication) {
        if (billService.existsById(id)){
            Bills bill = billService.findById(id).get();
            Users customer = iUserService.findByAccount(authentication.getName());
            if (customer==null){
                return ResponseEntity.badRequest().body("Không lấy được thông tin đăng nhập");
            }
            if (customer.getUserId().equals(bill.getCustomer().getUserId())){
                if (bill.getBillStatus()==5){
                    bill.setBillReceiverDate(new Date());
                    bill.setBillStatus(6);
                    billService.save(bill);
                    return ResponseEntity.ok("Đã nhận hàng thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể nhận đơn đang giao hàng !");
                }
            }else {
                return ResponseEntity.badRequest().body("Bạn không có quyền thực hiện");
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bill/giao-hang/{id}")
    public ResponseEntity giaoHang(@PathVariable String id,Authentication authentication) {
        if (billService.existsById(id)){
            Bills bill = billService.findById(id).get();


                if (bill.getBillStatus()==3){
                    bill.setBillShipDate(new Date());
                    bill.setShippingAddress(bill.getBillingAddress());
                    bill.setBillStatus(5);
                    billService.save(bill);
                    return ResponseEntity.ok("Đã nhận giao hàng thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể hủy khi đơn đã xác nhận !");
                }


        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bill/hoan-thanh/{id}")
    public ResponseEntity hoaThanh(@PathVariable String id,Authentication authentication) {
        if (billService.existsById(id)){
            Bills bill = billService.findById(id).get();

            if (bill.getBillStatus()==6){
                bill.setBillStatus(7);
                billService.save(bill);
                return ResponseEntity.ok("Đã nhận thành công");

            } else {
                return ResponseEntity.badRequest().body("Chỉ có thể hoàn thành khi đơn hàng đã giao !");
            }


        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bill/xac-nhan/{id}")
    public ResponseEntity xacnhan(@PathVariable String id,Authentication authentication) {
        if (billService.existsById(id)){
            Bills bill = billService.findById(id).get();
            Users staff = iUserService.findByAccount(authentication.getName());
            if (staff==null){
                return ResponseEntity.badRequest().body("Không lấy được thông tin đăng nhập");
            }

                if (bill.getBillStatus()==1){
                    bill.setStaff(staff);
                    bill.setBillStatus(3);
                    var billDetails = billDetailService.findAllByBillsBillId(bill.getBillId());
                    List<ProductDetails>productDetails= new ArrayList<>();
                    for (BillDetails billDetail : billDetails) {
                        ProductDetails productDetail = billDetail.getProductDetails();
                        productDetail.setProductDetailAmount(productDetail.getProductDetailAmount()-billDetail.getAmount());
                        if (productDetail.getProductDetailAmount()<0){
                            return ResponseEntity.badRequest().body("Không đủ số lượng sản phẩm !");
                        }
                        productDetails.add(productDetail);
                    }
                    productDetailService.saveAll(productDetails);
                    billService.save(bill);
                    return ResponseEntity.ok("Xác nhận thành công");

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể xác nhận đơn hàng chờ xử lí !");
                }


        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
