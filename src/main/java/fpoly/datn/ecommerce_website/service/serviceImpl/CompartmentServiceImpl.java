package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.entity.Compartments;
import fpoly.datn.ecommerce_website.repository.IBuckleTypeRepository;
import fpoly.datn.ecommerce_website.repository.ICompartmentRepository;
import fpoly.datn.ecommerce_website.service.CompartmentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompartmentServiceImpl implements CompartmentService {
    @Autowired
    private ICompartmentRepository iCompartmentRepository;

    @Override
    public List<Compartments> findAll() {
        return iCompartmentRepository.findAll();
    }

    @Override
    public Page<Compartments> findAllPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.iCompartmentRepository.findAllPagination(pageable);
    }

    @Override
    public Compartments findById(String id) {
        return iCompartmentRepository.findById(id).get();
    }

    @Override
    public Compartments save(Compartments entity) {
        return iCompartmentRepository.save(entity);
    }

    @Override
    public Compartments update(Compartments entity) {
        return iCompartmentRepository.save((entity));
    }

    @Override
    public Compartments updateStatus(String id, Integer status) {
        Compartments x = iCompartmentRepository.findById(id).get();
        x.setCompartmentStatus(status);

        return iCompartmentRepository.save(x);

    }

    @Override
    public String delete(String id) {
        iCompartmentRepository.deleteById(id);
        return "Delete successfully";
    }
}

