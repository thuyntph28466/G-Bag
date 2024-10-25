package fpoly.datn.ecommerce_website.restController.client;

import fpoly.datn.ecommerce_website.dto.ImageDTO;
import fpoly.datn.ecommerce_website.service.IImagesService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ImageRestController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IImagesService iImagesService;

    // getAll()
    @RequestMapping(value = "/image/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(iImagesService.findAll());
    }
    // add
    @RequestMapping(value = "/image/create", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody @Valid ImageDTO imageDTO){
        return new ResponseEntity<>(this.iImagesService.save(imageDTO), HttpStatus.OK);
    }
    // delete
    @RequestMapping (value = "/image/delele", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String imageId){
        return new ResponseEntity<>(this.iImagesService.delete(imageId),HttpStatus.OK);
    }


}
