package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.entity.Bills;
import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.service.serviceImpl.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BillService billService;
    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
    ) {
        List<Bills> bills = billService.findAll();
        return new ResponseEntity<>
                (bills, HttpStatus.OK);
    }
}
