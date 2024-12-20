package fpoly.datn.ecommerce_website.service;

import java.util.List;
import java.util.UUID;

public interface ServiceGenarel<T> {

    List<T> findAll();

    T findById(String id);

    T save(T entity);

    T update(T entity);

    String delete(String id);

    List<T> searchByName(String name);

}
