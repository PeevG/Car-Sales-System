package softuni.carsalessystem.services.impl;

import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.entities.BrandEntity;
import softuni.carsalessystem.repositories.BrandRepository;
import softuni.carsalessystem.services.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void seedBrands() {
        if (brandRepository.count() > 0) {
            return;
        }
        BrandEntity mercedes = new BrandEntity();
        mercedes.setName("Mercedes");
        this.brandRepository.save(mercedes);
    }
}
