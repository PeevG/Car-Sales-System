package softuni.carsalessystem.services.impl;

import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.entities.BrandEntity;
import softuni.carsalessystem.repositories.BrandRepository;
import softuni.carsalessystem.services.BrandService;

import java.util.List;

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
        BrandEntity bmw = new BrandEntity();
        bmw.setName("BMW");

        BrandEntity audi = new BrandEntity();
        audi.setName("Audi");

        BrandEntity opel = new BrandEntity();
        opel.setName("Opel");

        BrandEntity honda = new BrandEntity();
        honda.setName("Honda");

        BrandEntity mercedes = new BrandEntity();
        mercedes.setName("Mercedes");

        this.brandRepository.saveAll(List.of(bmw, audi, opel, honda, mercedes));
    }
}
