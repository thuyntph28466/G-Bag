package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.dto.ImageDTO;
import fpoly.datn.ecommerce_website.entity.Images;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IImagesService {
    List<ImageDTO> findAll();

    @Transactional
    @Modifying
    void deleteAllByProductDetailsProductDetailId(String productDetailId);

    Optional<Images> findById(String s);

    ImageDTO save(ImageDTO imageDTO);

    Boolean delete(String imageId);
}
