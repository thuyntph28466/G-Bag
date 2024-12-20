package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.VoucherDTO;
import fpoly.datn.ecommerce_website.entity.Vouchers;
import fpoly.datn.ecommerce_website.service.IVoucherService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class VoucherRestController {
    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private ModelMapper modelMapper;

    //GetAllPage
    @RequestMapping(value = "/voucher/pagination", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0") int pageNum,
            @RequestParam(name = "size", defaultValue = "15") int pageSize
    ) {
        Page<Vouchers> voucherPage = voucherService.findAllPage(pageNum, pageSize);
        return new ResponseEntity<>
                (voucherPage, HttpStatus.OK);
    }

    //GetAllList
    @RequestMapping(value = "/voucher/", method = RequestMethod.GET)
    public ResponseEntity<List<VoucherDTO>> getAll() {
        return new ResponseEntity<>(
                this.voucherService.fillAll()
                        .stream()
                        .map(voucher -> modelMapper.map(voucher, VoucherDTO.class))
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }


    //getOne

    //add
    @RequestMapping(value = "/voucher", method = RequestMethod.POST)
    public ResponseEntity<Vouchers> save(@Valid @RequestBody VoucherDTO voucherDTO) {
        Vouchers voucher = modelMapper.map(voucherDTO, Vouchers.class);
        return new ResponseEntity<>(
                this.voucherService.add(voucher)
                , HttpStatus.OK);
    }



    @RequestMapping(value = "/voucher/findByVoucherCode", method = RequestMethod.GET)
    public ResponseEntity<?> findByVoucherCode(@Valid @RequestParam String voucherCode) {

        return new ResponseEntity<>(
                this.voucherService.findByVoucherCode(voucherCode)
                , HttpStatus.OK);
    }

    //update
    @RequestMapping(value = "/voucher", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody VoucherDTO voucherDTO) {
        Vouchers vouchers = modelMapper.map(voucherDTO, Vouchers.class);
        return new ResponseEntity<>(
                this.voucherService.update(vouchers)
                , HttpStatus.OK
        );
    }


    //update status
    @RequestMapping(value = "/voucher/update-status", method = RequestMethod.PUT)
    public ResponseEntity<Vouchers> updateStatus(@Valid @RequestParam String id, @RequestParam int status) {
        return new ResponseEntity<>(voucherService.updateStatus(id, status),
                HttpStatus.OK);

    }
    @RequestMapping(value = "/voucher/update-amount", method = RequestMethod.PUT)
    public ResponseEntity<Vouchers> updateAmount(@Valid @RequestParam String voucherId, @RequestParam int amount) {
        return new ResponseEntity<>(voucherService.updateAmountBeforeAplied(voucherId, amount),
                HttpStatus.OK);

    }

}
