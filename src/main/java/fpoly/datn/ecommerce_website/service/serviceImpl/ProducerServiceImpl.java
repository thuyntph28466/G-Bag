package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.entity.Producers;
import fpoly.datn.ecommerce_website.repository.IProducerRepository;
import fpoly.datn.ecommerce_website.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements IProducerService {

    @Autowired
    private IProducerRepository producerRepository;

    @Override
    public List<Producers> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Page<Producers> findAllPagination(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return this.producerRepository.findAllPagination(pageable);
    }

    @Override
    public Producers findById(String id) {
        return producerRepository.findById(id).get();
    }

    @Override
    public Producers save(Producers entity) {
        producerRepository.save(entity);
        return entity;
    }

    @Override
    public Producers update(Producers entity) {
        return this.producerRepository.save(entity);
    }



    @Override
    public Producers updateStatus(String id, Integer status) {
        Producers x = producerRepository.findById(id).get();
        x.setProducerStatus(status);
        return producerRepository.save(x);
    }

    @Override
    public String delete(String id) {
        producerRepository.deleteById(id);
        return "Delete successfully";

    }

}
