package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.entity.Compartments;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompartmentService {
    List<Compartments> findAll();

    Page<Compartments> findAllPagination(Integer page, Integer size);

    Compartments findById(String id);

    Compartments save(Compartments entity);

    Compartments update(Compartments entity);

    Compartments updateStatus(String id, Integer status);

    String delete(String id);
}
