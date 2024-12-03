package fpoly.datn.ecommerce_website.restController.client;


import fpoly.datn.ecommerce_website.dto.MaterialDTO;
import fpoly.datn.ecommerce_website.dto.SizeDTO;
import fpoly.datn.ecommerce_website.dto.TypeDTO;
import fpoly.datn.ecommerce_website.entity.Materials;
import fpoly.datn.ecommerce_website.entity.Sizes;
import fpoly.datn.ecommerce_website.entity.Types;
import fpoly.datn.ecommerce_website.service.TypeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api")
public class TypeRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TypeService typeService;


    //GetAll
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Types> colorsList = this.typeService.findAll();
        List<Types> filtered = colorsList.stream()
                .filter(types -> types.getTypeStatus() == 1)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                filtered
                , HttpStatus.OK
        );
    }

    //PhanTrang
    @RequestMapping(value = "/type/pagination", method = RequestMethod.GET)
    public ResponseEntity<?> phanTrang(@RequestParam(name = "page", defaultValue = "0") int pageNum,
                                       @RequestParam(name = "size", defaultValue = "15") int pageSize){
        return ResponseEntity.ok(typeService.findAllPagination(pageNum, pageSize));
    }

    //GetOne
    @RequestMapping(value = "/type/", method = RequestMethod.GET)
    public ResponseEntity<TypeDTO> getOne(@RequestParam String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.typeService.findById(id), TypeDTO.class)
                , HttpStatus.OK);
    }

    //Add
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    public ResponseEntity<Types> save(@RequestBody TypeDTO typeDTO) {
        Types type = modelMapper.map(typeDTO, Types.class);
        return new ResponseEntity<>(
                this.typeService.save(type)
                , HttpStatus.OK);
    }


        @RequestMapping(value = "/type/update-status", method = RequestMethod.PUT)
    public ResponseEntity<Types> updateStatus(@Valid @RequestParam String id, @RequestParam int status) {
        return new ResponseEntity<>(typeService.updateStatus(id, status),
                HttpStatus.OK);

    }

    //Delete

    @RequestMapping(value = "/type", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String id) {
        return new ResponseEntity<>(this.typeService.delete(id), HttpStatus.OK);

    }
    //End
}
