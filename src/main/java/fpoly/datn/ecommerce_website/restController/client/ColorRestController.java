package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.ColorDTO;
import fpoly.datn.ecommerce_website.dto.CompartmentDTO;
import fpoly.datn.ecommerce_website.entity.Colors;
import fpoly.datn.ecommerce_website.service.serviceImpl.ColorServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class ColorRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ColorServiceImpl colorService;


    @RequestMapping(value = "/color/pagination", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginantion(
            @RequestParam(name = "page", defaultValue = "0") int pageNum,
            @RequestParam(name = "size", defaultValue = "15") int pageSize
    ) {
        Page<Colors> colorPage = colorService.findAllPage(pageNum, pageSize);
        return new ResponseEntity<>
                (colorPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(

    ) {
        List<Colors> allColors  = colorService.findAll();
        List<Colors> filteredColors = allColors;

        return new ResponseEntity<>
                (filteredColors, HttpStatus.OK);
    }

    @RequestMapping(value = "/color/one", method = RequestMethod.GET)
    public ResponseEntity<ColorDTO> getOne(@RequestParam String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.colorService.findById(id), ColorDTO.class)
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/color", method = RequestMethod.POST)
    public ResponseEntity<Colors> save(@RequestBody ColorDTO colorDTO) {
        Colors color = modelMapper.map(colorDTO, Colors.class);
        color.setColorId(null);
        return new ResponseEntity<>(
                this.colorService.save(color)
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/color/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Colors> update(@PathVariable String id, @RequestBody ColorDTO colorDTO) {
        Colors color = modelMapper.map(colorDTO, Colors.class);
        color.setColorId(id);

        // Kiểm tra xem màu sắc có tồn tại trong cơ sở dữ liệu không
        Colors updatedColor = this.colorService.save(color);
        if (updatedColor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Nếu không tìm thấy
        }

        return new ResponseEntity<>(updatedColor, HttpStatus.OK); // Cập nhật thành công
    }



    @RequestMapping(value = "/color/update-status", method = RequestMethod.PUT)
    public ResponseEntity<Colors> updateStatus(@Valid @RequestParam String id, @RequestParam int status) {
        return new ResponseEntity<>(colorService.updateStatus(id, status),
                HttpStatus.OK);

    }


    @RequestMapping(value = "/color", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String id) {
        this.colorService.delete(id);
        return new ResponseEntity<>(
                "Delete Successfully"
                , HttpStatus.OK
        );
    }

}
