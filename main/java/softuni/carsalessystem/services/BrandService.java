package softuni.carsalessystem.services;

import softuni.carsalessystem.models.bindings.BrandDto;
import softuni.carsalessystem.models.entities.BrandEntity;

import java.util.List;

public interface BrandService {
    void seedBrands();
    List<BrandDto> getAllBrands();
}
