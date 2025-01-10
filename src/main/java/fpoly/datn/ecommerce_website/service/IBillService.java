package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.entity.Bills;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface IBillService {
    void flush();

    <S extends Bills> S saveAndFlush(S entity);

    <S extends Bills> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Bills> entities);

    void deleteAllInBatch(Iterable<Bills> entities);

    void deleteAllByIdInBatch(Iterable<String> strings);

    void deleteAllInBatch();

    @Deprecated
    Bills getOne(String s);

    @Deprecated
    Bills getById(String s);

    Bills getReferenceById(String s);

    <S extends Bills> List<S> findAll(Example<S> example);

    <S extends Bills> List<S> findAll(Example<S> example, Sort sort);

    <S extends Bills> List<S> saveAll(Iterable<S> entities);

    List<Bills> findAll();

    List<Bills> findAllById(Iterable<String> strings);

    <S extends Bills> S save(S entity);

    Optional<Bills> findById(String s);

    boolean existsById(String s);

    long count();

    void deleteById(String s);

    void delete(Bills entity);

    void deleteAllById(Iterable<? extends String> strings);

    void deleteAll(Iterable<? extends Bills> entities);

    void deleteAll();

    List<Bills> findAll(Sort sort);

    Page<Bills> findAll(Pageable pageable);

    <S extends Bills> Optional<S> findOne(Example<S> example);

    <S extends Bills> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Bills> long count(Example<S> example);

    <S extends Bills> boolean exists(Example<S> example);

    <S extends Bills, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
