package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.BillDetails;
import fpoly.datn.ecommerce_website.repository.IBillDetailsReponsitory;
import fpoly.datn.ecommerce_website.service.IBillDetailService;
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
public class BillDetailService implements IBillDetailService {
    @Autowired
    IBillDetailsReponsitory iBillDetailsReponsitory;

    @Override
    public void flush() {
        iBillDetailsReponsitory.flush();
    }

    @Override
    public <S extends BillDetails> S saveAndFlush(S entity) {
        return iBillDetailsReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends BillDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
        return iBillDetailsReponsitory.saveAllAndFlush(entities);
    }

    @Override
    public List<BillDetails> findAllByBillsBillId(String billsBillId) {
        return iBillDetailsReponsitory.findAllByBillsBillId(billsBillId);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<BillDetails> entities) {
        iBillDetailsReponsitory.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<BillDetails> entities) {
        iBillDetailsReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {
        iBillDetailsReponsitory.deleteAllByIdInBatch(strings);
    }

    @Override
    public void deleteAllInBatch() {
        iBillDetailsReponsitory.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public BillDetails getOne(String s) {
        return iBillDetailsReponsitory.getOne(s);
    }

    @Override
    @Deprecated
    public BillDetails getById(String s) {
        return iBillDetailsReponsitory.getById(s);
    }

    @Override
    public BillDetails getReferenceById(String s) {
        return iBillDetailsReponsitory.getReferenceById(s);
    }

    @Override
    public <S extends BillDetails> List<S> findAll(Example<S> example) {
        return iBillDetailsReponsitory.findAll(example);
    }

    @Override
    public <S extends BillDetails> List<S> findAll(Example<S> example, Sort sort) {
        return iBillDetailsReponsitory.findAll(example, sort);
    }

    @Override
    public <S extends BillDetails> List<S> saveAll(Iterable<S> entities) {
        return iBillDetailsReponsitory.saveAll(entities);
    }

    @Override
    public List<BillDetails> findAll() {
        return iBillDetailsReponsitory.findAll();
    }

    @Override
    public List<BillDetails> findAllById(Iterable<String> strings) {
        return iBillDetailsReponsitory.findAllById(strings);
    }

    @Override
    public <S extends BillDetails> S save(S entity) {
        return iBillDetailsReponsitory.save(entity);
    }

    @Override
    public Optional<BillDetails> findById(String s) {
        return iBillDetailsReponsitory.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return iBillDetailsReponsitory.existsById(s);
    }

    @Override
    public long count() {
        return iBillDetailsReponsitory.count();
    }

    @Override
    public void deleteById(String s) {
        iBillDetailsReponsitory.deleteById(s);
    }

    @Override
    public void delete(BillDetails entity) {
        iBillDetailsReponsitory.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        iBillDetailsReponsitory.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends BillDetails> entities) {
        iBillDetailsReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        iBillDetailsReponsitory.deleteAll();
    }

    @Override
    public List<BillDetails> findAll(Sort sort) {
        return iBillDetailsReponsitory.findAll(sort);
    }

    @Override
    public Page<BillDetails> findAll(Pageable pageable) {
        return iBillDetailsReponsitory.findAll(pageable);
    }

    @Override
    public <S extends BillDetails> Optional<S> findOne(Example<S> example) {
        return iBillDetailsReponsitory.findOne(example);
    }

    @Override
    public <S extends BillDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return iBillDetailsReponsitory.findAll(example, pageable);
    }

    @Override
    public <S extends BillDetails> long count(Example<S> example) {
        return iBillDetailsReponsitory.count(example);
    }

    @Override
    public <S extends BillDetails> boolean exists(Example<S> example) {
        return iBillDetailsReponsitory.exists(example);
    }

    @Override
    public <S extends BillDetails, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return iBillDetailsReponsitory.findBy(example, queryFunction);
    }
}
