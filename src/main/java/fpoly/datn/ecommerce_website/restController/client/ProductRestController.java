package fpoly.datn.ecommerce_website.restController.client;


import fpoly.datn.ecommerce_website.dto.ProductDTO;
import fpoly.datn.ecommerce_website.dto.Product_BrandDTO;
import fpoly.datn.ecommerce_website.entity.Products;
import fpoly.datn.ecommerce_website.repository.IProductRepository;
import fpoly.datn.ecommerce_website.service.IProductService;
import fpoly.datn.ecommerce_website.service.serviceImpl.BrandServiceImpl;
import fpoly.datn.ecommerce_website.service.serviceImpl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductRestController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductService productService;
    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    private IProductRepository productRepository;

    public ProductRestController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    //hienthi
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false) Integer pageNum,
            @RequestParam(name = "size", required = false) Integer pageSize,
            @RequestParam(name = "productName", required = false, defaultValue = "") String productName,
            @RequestParam(name = "productCode", required = false, defaultValue = "") String productCode,
            @RequestParam(name = "brandName", required = false, defaultValue = "") String brandName,
            @RequestParam(name = "productStatus", required = false, defaultValue = "") Integer productStatus,
            @RequestParam(defaultValue = "") List<String> sortList,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder
    ) {
        if (pageNum == null || pageSize == null) {
            return new ResponseEntity<>
                    (this.productService.findAll(), HttpStatus.OK);
        }
        Page<ProductDTO> productPage = productService.findAllPagination(
                pageNum,
                pageSize,
                productName,
                productCode,
                brandName,
                productStatus,
                sortList,
                sortOrder.toString());
        return new ResponseEntity<>
                (productPage, HttpStatus.OK);
    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.productService.findById(id), ProductDTO.class)
                , HttpStatus.OK);
    }

    //add
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody Product_BrandDTO productBrandDTO) {
        Products product = modelMapper.map(productBrandDTO, Products.class);
product.setProductId(null);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {


        return new ResponseEntity<>(productService.test(), HttpStatus.OK);
    }

    //update
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.save(
                modelMapper.map(productDTO, Products.class)

        ), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/update-status", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@Valid @RequestParam String productID, @RequestParam int status) {
        return new ResponseEntity<>(productService.updateProductStatus(
                productID, status

        ), HttpStatus.OK);
    }

    //delete
    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@RequestParam("id") String id) {
        productService.delete(productService.findById(id).getProductId());
        return new ResponseEntity<>("Delete Successfully!!!!!!", HttpStatus.NO_CONTENT);
    }
    //End
}
