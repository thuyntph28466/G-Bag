package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.ImageDTO;
import fpoly.datn.ecommerce_website.dto.ProductDTO;
import fpoly.datn.ecommerce_website.dto.ProductDetailDTO;
import fpoly.datn.ecommerce_website.entity.Images;
import fpoly.datn.ecommerce_website.entity.ProductDetails;
import fpoly.datn.ecommerce_website.entity.Products;
import fpoly.datn.ecommerce_website.service.IImagesService;
import fpoly.datn.ecommerce_website.service.serviceImpl.ProductDetailServiceImpl;
import fpoly.datn.ecommerce_website.service.serviceImpl.ProductServiceImpl;
import fpoly.datn.ecommerce_website.util.FileImgUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductDetailRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDetailServiceImpl productDetailService;
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private IImagesService imagesService;
    @Autowired
    private FileImgUtil fileImgUtil;

    //getOne
    @RequestMapping(value = "/product-details/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable String id) {
        ProductDetails productDetails = this.productDetailService.findById(id);
        if (productDetails == null) {
            return new ResponseEntity<>(
                    null
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                modelMapper.map(productDetails, ProductDetailDTO.class)
                , HttpStatus.OK);
    }

// getall
    @RequestMapping(value = "/product-details", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false) Integer pageNum,
            @RequestParam(name = "size", required = false) Integer pageSize,
            @RequestParam(name = "productName", required = false, defaultValue = "") String productName,
            @RequestParam(name = "productCode", required = false, defaultValue = "") String productCode,
            @RequestParam(name = "colorName", required = false, defaultValue = "") String colorName,
            @RequestParam(name = "typeName", required = false, defaultValue = "") String typeName,
            @RequestParam(name = "materialName", required = false, defaultValue = "") String materialName,
            @RequestParam(name = "sizeName", required = false, defaultValue = "") String sizeName,
            @RequestParam(name = "brandName", required = false, defaultValue = "") String brandName,
            @RequestParam(name = "compartmentName", required = false, defaultValue = "") String compartmentName,
            @RequestParam(name = "producerName", required = false, defaultValue = "") String producerName,
            @RequestParam(name = "buckleTypeName", required = false, defaultValue = "") String buckleTypeName,
            @RequestParam(name = "productDetailDescribe", required = false, defaultValue = "") String productDetailDescribe,
            @RequestParam(name = "minProductDetailAmount", required = false ) Integer minProductDetailAmount,
            @RequestParam(name = "maxProductDetailAmount", required = false ) Integer maxProductDetailAmount,
            @RequestParam(name = "minImportPrice", required = false ) Integer minImportPrice,
            @RequestParam(name = "maxImportPrice", required = false ) Integer maxImportPrice,
            @RequestParam(name = "minRetailPrice", required = false ) Integer minRetailPrice,
            @RequestParam(name = "maxRetailPrice", required = false ) Integer maxRetailPrice,
            @RequestParam(name = "productDetailStatus", required = false,  defaultValue = "") Integer productDetailStatus,
            @RequestParam(defaultValue = "") List<String> sortList,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder
    ) {
        if (pageNum == null || pageSize == null) {
         pageNum = 0 ;
         pageSize=100;
        }
        Page<ProductDetailDTO> productPage = productDetailService.findAllPagination(
                pageNum,
                pageSize,
                productName,
                productCode,
                colorName,
                typeName,
                materialName,
                sizeName,
                brandName,
                compartmentName,
                producerName,
                buckleTypeName,
                productDetailDescribe,
                minProductDetailAmount,
                maxProductDetailAmount,
                minImportPrice,
                maxImportPrice,
                minRetailPrice,
                maxRetailPrice,
                productDetailStatus,
                sortList,
                sortOrder.toString()
        );

        return new ResponseEntity<>
                (productPage, HttpStatus.OK);
    }

    //add
    @RequestMapping(value = "/product-details", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> save(@Valid @ModelAttribute ProductDetailDTO productDetailDTO,@RequestParam(value = "image",required = false) MultipartFile image) {
      if(productDetailDTO.getImportPrice().doubleValue()>productDetailDTO.getRetailPrice().doubleValue()){
          return new ResponseEntity<>(
                  "Giá bán phải lớn hơn giá nhập "
                  , HttpStatus.BAD_REQUEST);
      }
        if(productDetailDTO.getImportPrice().doubleValue()<=0){
            return new ResponseEntity<>(
                    "Giá nhập phải lớn hơn 0 "
                    , HttpStatus.BAD_REQUEST);
        }
        if(productDetailDTO.getRetailPrice().doubleValue()<=0){
            return new ResponseEntity<>(
                    "Giá bán phải lớn hơn 0 "
                    , HttpStatus.BAD_REQUEST);
        }
        if(productDetailDTO.getProductDetailAmount().doubleValue()<=0){
            return new ResponseEntity<>(
                    "Số Lượng lớn hơn 0 "
                    , HttpStatus.BAD_REQUEST);
        }

        if(productDetailDTO.getProduct().getProductCode().trim().isEmpty()){
            return new ResponseEntity<>(
                    "Mã balo không được để trống "
                    , HttpStatus.BAD_REQUEST);
        }
        if(productDetailDTO.getProduct().getProductCode().trim().isEmpty()){
            return new ResponseEntity<>(
                    "Mã balo không được để trống "
                    , HttpStatus.BAD_REQUEST);
        }
        if(productDetailDTO.getProduct().getProductName().trim().isEmpty()){
            return new ResponseEntity<>(
                    "Tên balo không được để trống "
                    , HttpStatus.BAD_REQUEST);
        }

        System.out.println(productDetailDTO);
        Products productDTO = new Products();
        productDTO.setProductId(null);
        productDTO.setProductName(productDetailDTO.getProduct().getProductName());
        productDTO.setProductCode(productDetailDTO.getProduct().getProductCode());
        productDTO.setBrand(productDetailDTO.getProduct().getBrand());
        productDTO.setType(productDetailDTO.getProduct().getType());
        productDTO.setMaterial(productDetailDTO.getProduct().getMaterial());
        productDTO.setSize(productDetailDTO.getProduct().getSize());
        productDTO.setCompartment(productDetailDTO.getProduct().getCompartment());
        productDTO.setBuckleType(productDetailDTO.getProduct().getBuckleType());
         productDTO.setProducer(productDetailDTO.getProduct().getProducer());
        productDTO.setProductStatus(productDetailDTO.getProduct().getProductStatus());



        if (productDetailDTO.getProduct().getProductId().isBlank()){

            if(productService.existsByProductCode(productDetailDTO.getProduct().getProductCode())){
                return new ResponseEntity<>(
                        "Mã balo đã tồn tại "
                        , HttpStatus.BAD_REQUEST);
            }
            Products productsaved = productService.save(productDTO);
            productDetailDTO.setProduct(productsaved);
        }



           var productDetailInDB =  productDetailService.findByProductProductIdAndColorColorId(productDetailDTO.getProduct().getProductId(),productDetailDTO.getColor().getColorId());
          if (productDetailInDB!=null){
              productDetailDTO.setProductDetailId(productDetailInDB.getProductDetailId());

          }else{
              productDetailDTO.setProductDetailId(null);
          }



        ImageDTO imageDTO = new ImageDTO();

        if (image == null || image.isEmpty()) {

        }else {
            imageDTO.setImageId(null);
            imageDTO.setIsPrimary(Boolean.TRUE);
            try {
                imageDTO.setData(new SerialBlob(image.getBytes()));
            } catch (SQLException|IOException e ) {
                return new ResponseEntity<>(
                        "Thêm không thành công kiểm tra lại ảnh"
                        , HttpStatus.OK);
            }
        }


        ProductDetailDTO productDetailDTO1 =  productDetailService.save(productDetailDTO);
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductDetailId(productDetailDTO1.getProductDetailId());
        if (!(image == null || image.isEmpty())) {
            imageDTO.setProductDetails(productDetails);
            imagesService.deleteAllByProductDetailsProductDetailId(productDetailDTO1.getProductDetailId());
            imagesService.save(imageDTO);
        }




        return new ResponseEntity<>(
                productDetailDTO
                , HttpStatus.OK);
    }



//    //update
    @RequestMapping(value = "/product-details/update-status", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam String productDetailId, @RequestParam int status) {
        return new ResponseEntity<>(
                productDetailService.updateStatus(productDetailId, status)
                , HttpStatus.OK);
    }


//    //delete
    @RequestMapping(value = "/product-details", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String productDetailId) {
        productDetailService.delete(productDetailId);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
//
    @RequestMapping(value = "product-detail/{productCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllbyproduct(@PathVariable String productCode) {
        return new ResponseEntity<>(
                this.productDetailService.findAllByProductCode(productCode)
                        .stream()
                        .map(productDetail -> modelMapper.map(productDetail, ProductDetailDTO.class))
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );
    }
    @RequestMapping(value = "product-detail/getProductDetailsByProductId/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllbyproductId(@PathVariable String productId) {
        return new ResponseEntity<>(
                this.productDetailService.findAllByProductId(productId)
                        .stream()
                        .map(productDetail -> modelMapper.map(productDetail, ProductDetailDTO.class))
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );

    }
//
//

    @RequestMapping(value = "/product-details/search", method = RequestMethod.GET)
    public ResponseEntity<?> findByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<>(
                this.productDetailService.findByKeyword(keyword).stream()
                        .map(productDetail -> modelMapper.map(productDetail, ProductDetailDTO.class))
                        .collect(Collectors.toList())
                , HttpStatus.OK);

    }

        @RequestMapping(value = "/product-detail/update-amount", method = RequestMethod.POST)
        public ResponseEntity<?> updateAmount(
                @RequestParam @NotNull String productDetailId,
                @RequestParam @NotNull Integer amount) {
            ProductDetails productDetails = this.productDetailService.findById(productDetailId);


            if(productDetails.getProductDetailAmount() < amount) {

                return  new ResponseEntity<>( "Số lượng update không hợp lệ!!!"
                        , HttpStatus.CONFLICT);
            }
            int newAmount = productDetails.getProductDetailAmount()-amount;
            productDetails.setProductDetailAmount(newAmount);

            if(productDetails.getProductDetailAmount() == 0 ){
                productDetails.setProductDetailStatus(0);
            }
            return new ResponseEntity<>(
                    modelMapper.map(this.productDetailService.save(modelMapper.map(productDetails, ProductDetailDTO.class)), ProductDetailDTO.class)
                    , HttpStatus.OK);
        }



    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        Optional<Images> image = imagesService.findById(id);
        if (!image.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tồn tại");
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = fileImgUtil.convertBlobToByteArray(image.get().getData());
        } catch (SQLException |IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lỗi đọc ảnh");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }





}
