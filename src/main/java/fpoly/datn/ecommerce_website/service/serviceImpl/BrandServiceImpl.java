package fpoly.datn.ecommerce_website.service.serviceImpl;

import fpoly.datn.ecommerce_website.entity.Brands;
import fpoly.datn.ecommerce_website.entity.BuckleTypes;
import fpoly.datn.ecommerce_website.repository.IBrandRepository;
import fpoly.datn.ecommerce_website.repository.IBuckleTypeRepository;
import fpoly.datn.ecommerce_website.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private IBrandRepository iBrandRepository;

    @Override
    public List<Brands> findAll() {
        return iBrandRepository.findAll();
    }

    @Override
    public Page<Brands> findAllPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return this.iBrandRepository.findAllPagination(pageable);
    }

    @Override
    public Brands findById(String id) {
        return iBrandRepository.findById(id).get();
    }

    @Override
    public Brands save(Brands entity) {

        return iBrandRepository.save(entity);
    }

    @Override
    public Brands update( Brands entity) {
        return
                iBrandRepository.save((entity)) ;
    }

    @Override
    public Brands updateStatus(String id, Integer status) {
        Brands x = iBrandRepository.findById(id).get();
        x.setBrandStatus(status);
        return iBrandRepository.save(x);

    }

    @Override
    public String delete(String id) {
        iBrandRepository.deleteById(id);
        return "Delete successfully";
    }


}
