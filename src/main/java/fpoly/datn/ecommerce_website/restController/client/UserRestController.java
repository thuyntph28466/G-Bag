package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.BrandDTO;
import fpoly.datn.ecommerce_website.dto.ColorDTO;
import fpoly.datn.ecommerce_website.dto.UserDTO;
import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.entity.Colors;
import fpoly.datn.ecommerce_website.entity.Users;
import fpoly.datn.ecommerce_website.service.serviceImpl.BrandServiceImpl;
import fpoly.datn.ecommerce_website.service.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserRestController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserServiceImpl userService;

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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
    ) {
        List<Users> brandPage = userService.findAll();
        return new ResponseEntity<>
                (brandPage, HttpStatus.OK);
    }

    //GetOne
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getOne(@Valid @RequestParam String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.userService.findById(id), UserDTO.class)
                , HttpStatus.OK);
    }

    //Add
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }

        // Chuyển đổi từ DTO sang Entity
        Users user = modelMapper.map(userDTO, Users.class);
        user.setUserId(null);  // Không cần set userId nếu là UUID tự động

        Users savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }




    @RequestMapping(value = "/update/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> update(@PathVariable String id, @RequestBody UserDTO brandDTO) {
        // Chuyển đổi từ BrandDTO sang Brands (đối tượng thực tế cần cập nhật)
        Users brand = modelMapper.map(brandDTO, Users.class);

        // Gán ID cho đối tượng Brand
        brand.setUserId(id);

        // Cập nhật đối tượng Brand trong service
        Users updatedBrand = this.userService.update(brand);

        if (updatedBrand == null) {
            // Nếu không tìm thấy đối tượng để cập nhật, trả về mã lỗi 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Nếu cập nhật thành công, trả về đối tượng cập nhật và mã trạng thái OK
        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }





    //Delete
    @RequestMapping(value = "/delete/user", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String id) {
        this.userService.delete(id);
        return new ResponseEntity<>(
                "Delete Successfuly"
                , HttpStatus.OK);
    }


}
