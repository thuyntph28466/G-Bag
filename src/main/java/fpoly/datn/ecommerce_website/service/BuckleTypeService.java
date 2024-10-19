package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BuckleTypeService {
    List<BuckleTypes> findAll();

    Page<BuckleTypes> findAllPagination(Integer page, Integer size);

    BuckleTypes findById(String id);

    BuckleTypes save(BuckleTypes entity);

    BuckleTypes update(BuckleTypes entity);

    BuckleTypes updateStatus(String id, Integer status);

    String delete(String id);



}
