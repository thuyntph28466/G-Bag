package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.BrandDTO;
import fpoly.datn.ecommerce_website.dto.ColorDTO;
import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.entity.Colors;
import fpoly.datn.ecommerce_website.service.serviceImpl.BrandServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BrandRestController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandServiceImpl brandService;

    //GetAllPage
//    @RequestMapping(value = "/brand/pagination", method = RequestMethod.GET)
//    public ResponseEntity<?> getAll(
//            @RequestParam(name = "page", defaultValue = "0") int pageNum,
//            @RequestParam(name = "size", defaultValue = "15") int pageSize
//    ) {
//        Page<Brands> brandPage = brandService.findAllPagination(pageNum, pageSize);
//        return new ResponseEntity<>
//                (brandPage, HttpStatus.OK);
//    }

    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
    ) {
        List<Brands> brandPage = brandService.findAll();
        return new ResponseEntity<>
                (brandPage, HttpStatus.OK);
    }

    //GetOne
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
    public ResponseEntity<BrandDTO> getOne(@Valid @RequestParam String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.brandService.findById(id), BrandDTO.class)
                , HttpStatus.OK);
    }

    //Add
    @RequestMapping(value = "/brand/add", method = RequestMethod.POST)
    public ResponseEntity<Brands> save(@Valid @RequestBody BrandDTO brandDTO) {
        Brands brand = modelMapper.map(brandDTO, Brands.class);
        brand.setBrandId(null);
        return new ResponseEntity<>(
                this.brandService.save(brand)
                , HttpStatus.OK);
    }


//    @RequestMapping(value = "/brand/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Brands> update(@RequestBody BrandDTO brandDTO, @PathVariable("id") String id) {
//        Brands updatedBrand = this.brandService.update(brandDTO, id);
//        if (updatedBrand != null) {
//            return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Brands> update(@Valid @RequestParam String id, @RequestBody BrandDTO brandDTO) {
        Brands brand = modelMapper.map(brandDTO, Brands.class);
        brand.setBrandId(id);
        return new ResponseEntity<>(
                this.brandService.update(brand)
                , HttpStatus.OK);
    }




    //Delete
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String id) {
        this.brandService.delete(id);
        return new ResponseEntity<>(
                "Delete Successfuly"
                , HttpStatus.OK);
    }


}
