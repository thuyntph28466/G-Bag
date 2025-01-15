package fpoly.datn.ecommerce_website.service;

import fpoly.datn.ecommerce_website.entity.BillDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface IBillDetailService {
    void flush();

    <S extends BillDetails> S saveAndFlush(S entity);

    <S extends BillDetails> List<S> saveAllAndFlush(Iterable<S> entities);

    List<BillDetails> findAllByBillsBillId(String billsBillId);

    @Deprecated
    void deleteInBatch(Iterable<BillDetails> entities);

    void deleteAllInBatch(Iterable<BillDetails> entities);

    void deleteAllByIdInBatch(Iterable<String> strings);

    void deleteAllInBatch();

    @Deprecated
    BillDetails getOne(String s);

    @Deprecated
    BillDetails getById(String s);

    BillDetails getReferenceById(String s);

    <S extends BillDetails> List<S> findAll(Example<S> example);

    <S extends BillDetails> List<S> findAll(Example<S> example, Sort sort);

    <S extends BillDetails> List<S> saveAll(Iterable<S> entities);

    List<BillDetails> findAll();

    List<BillDetails> findAllById(Iterable<String> strings);

    <S extends BillDetails> S save(S entity);

    Optional<BillDetails> findById(String s);

    boolean existsById(String s);

    long count();

    void deleteById(String s);

    void delete(BillDetails entity);

    void deleteAllById(Iterable<? extends String> strings);

    void deleteAll(Iterable<? extends BillDetails> entities);

    void deleteAll();

    List<BillDetails> findAll(Sort sort);

    Page<BillDetails> findAll(Pageable pageable);

    <S extends BillDetails> Optional<S> findOne(Example<S> example);

    <S extends BillDetails> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends BillDetails> long count(Example<S> example);

    <S extends BillDetails> boolean exists(Example<S> example);

    <S extends BillDetails, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
