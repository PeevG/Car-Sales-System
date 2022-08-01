package softuni.carsalessystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.bindings.BrandDto;
import softuni.carsalessystem.models.entities.BrandEntity;
import softuni.carsalessystem.repositories.BrandRepository;
import softuni.carsalessystem.services.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<BrandDto> getAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();
        return brands
                .stream()
                .map(this::mapBrands)
                .collect(Collectors.toList());
    }

    private BrandDto mapBrands(BrandEntity brandEntity) {
        return modelMapper.map(brandEntity, BrandDto.class);
    }
}
