package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.repository.IBuckleTypeRepository;
import fpoly.datn.ecommerce_website.service.BuckleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuckleTypeServiceImpl implements BuckleTypeService {
    @Autowired
    private IBuckleTypeRepository iBuckleTypeRepository;

    @Override
    public List<BuckleTypes> findAll() {
        return iBuckleTypeRepository.findAll();
    }

    @Override
    public Page<BuckleTypes> findAllPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return this.iBuckleTypeRepository.findAllPagination(pageable);
    }

    @Override
    public BuckleTypes findById(String id) {
        return iBuckleTypeRepository.findById(id).get();
    }

    @Override
    public BuckleTypes save(BuckleTypes entity) {
        return iBuckleTypeRepository.save(entity);
    }

    @Override
    public BuckleTypes update( BuckleTypes entity) {
        return iBuckleTypeRepository.save((entity)) ;
    }

    @Override
    public BuckleTypes updateStatus(String id, Integer status) {
        BuckleTypes x = iBuckleTypeRepository.findById(id).get();
         x.setBuckleTypeStatus(status);
        return iBuckleTypeRepository.save(x);

    }

    @Override
    public String delete(String id) {
        iBuckleTypeRepository.deleteById(id);
        return "Delete successfully";
    }


}
