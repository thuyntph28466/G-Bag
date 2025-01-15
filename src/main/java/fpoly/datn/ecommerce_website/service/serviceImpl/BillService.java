package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.Bills;
import fpoly.datn.ecommerce_website.repository.IBillsReponsitory;
import fpoly.datn.ecommerce_website.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillsReponsitory iBillsReponsitory;

    @Override
    public void flush() {
        iBillsReponsitory.flush();
    }

    @Override
    public <S extends Bills> S saveAndFlush(S entity) {
        return iBillsReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends Bills> List<S> saveAllAndFlush(Iterable<S> entities) {
        return iBillsReponsitory.saveAllAndFlush(entities);
    }

    @Override
    public List<Bills> findAllByCustomerUserIdAndBillStatus(String customerId, Integer type) {
        return iBillsReponsitory.findAllByCustomerUserIdAndBillStatus(customerId, type);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Bills> entities) {
        iBillsReponsitory.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Bills> entities) {
        iBillsReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {
        iBillsReponsitory.deleteAllByIdInBatch(strings);
    }

    @Override
    public void deleteAllInBatch() {
        iBillsReponsitory.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Bills getOne(String s) {
        return iBillsReponsitory.getOne(s);
    }

    @Override
    @Deprecated
    public Bills getById(String s) {
        return iBillsReponsitory.getById(s);
    }

    @Override
    public Bills getReferenceById(String s) {
        return iBillsReponsitory.getReferenceById(s);
    }

    @Override
    public <S extends Bills> List<S> findAll(Example<S> example) {
        return iBillsReponsitory.findAll(example);
    }

    @Override
    public <S extends Bills> List<S> findAll(Example<S> example, Sort sort) {
        return iBillsReponsitory.findAll(example, sort);
    }

    @Override
    public <S extends Bills> List<S> saveAll(Iterable<S> entities) {
        return iBillsReponsitory.saveAll(entities);
    }

    @Override
    public List<Bills> findAll() {
        return iBillsReponsitory.findAll();
    }

    @Override
    public List<Bills> findAllByBillStatus(Integer type) {
        return iBillsReponsitory.findAllByBillStatus(type);
    }

    @Override
    public List<Bills> findAllById(Iterable<String> strings) {
        return iBillsReponsitory.findAllById(strings);
    }

    @Override
    public <S extends Bills> S save(S entity) {
        return iBillsReponsitory.save(entity);
    }

    @Override
    public Optional<Bills> findById(String s) {
        return iBillsReponsitory.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return iBillsReponsitory.existsById(s);
    }

    @Override
    public long count() {
        return iBillsReponsitory.count();
    }

    @Override
    public void deleteById(String s) {
        iBillsReponsitory.deleteById(s);
    }

    @Override
    public void delete(Bills entity) {
        iBillsReponsitory.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        iBillsReponsitory.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends Bills> entities) {
        iBillsReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        iBillsReponsitory.deleteAll();
    }

    @Override
    public List<Bills> findAll(Sort sort) {
        return iBillsReponsitory.findAll(sort);
    }

    @Override
    public Page<Bills> findAll(Pageable pageable) {
        return iBillsReponsitory.findAll(pageable);
    }

    @Override
    public <S extends Bills> Optional<S> findOne(Example<S> example) {
        return iBillsReponsitory.findOne(example);
    }

    @Override
    public <S extends Bills> Page<S> findAll(Example<S> example, Pageable pageable) {
        return iBillsReponsitory.findAll(example, pageable);
    }

    @Override
    public <S extends Bills> long count(Example<S> example) {
        return iBillsReponsitory.count(example);
    }

    @Override
    public <S extends Bills> boolean exists(Example<S> example) {
        return iBillsReponsitory.exists(example);
    }

    @Override
    public <S extends Bills, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return iBillsReponsitory.findBy(example, queryFunction);
    }
}
