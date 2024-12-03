package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.ImageDTO;
import fpoly.datn.ecommerce_website.entity.Images;

import java.util.List;
import java.util.Optional;

public interface IImagesService {
    List<ImageDTO> findAll();

    Optional<Images> findById(String s);

    ImageDTO save(ImageDTO imageDTO);

    Boolean delete(String imageId);
}
