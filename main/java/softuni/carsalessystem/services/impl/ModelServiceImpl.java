package softuni.carsalessystem.services.impl;

import org.springframework.stereotype.Service;
import softuni.carsalessystem.enums.CategoryEnum;
import softuni.carsalessystem.models.entities.BrandEntity;
import softuni.carsalessystem.models.entities.ModelEntity;
import softuni.carsalessystem.repositories.BrandRepository;
import softuni.carsalessystem.repositories.ModelRepository;
import softuni.carsalessystem.services.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public ModelServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void seedModels() {
        if (modelRepository.count() > 0) {
            return;
        }
        BrandEntity mercedes = this.brandRepository.findBrandEntityByName("Mercedes");
        BrandEntity bmw = this.brandRepository.findBrandEntityByName("BMW");
        BrandEntity honda = this.brandRepository.findBrandEntityByName("Honda");
        BrandEntity audi = this.brandRepository.findBrandEntityByName("Audi");
        BrandEntity opel = this.brandRepository.findBrandEntityByName("Opel");

        ModelEntity astra = new ModelEntity()
                .setBrand(opel)
                .setName("Astra")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://automedia.investor.bg/media/files/resized/gallery/760x/248/c1b964dacd29eedf51d157d6f45c7248-01-12.jpg")
                .setStartYear(1992);

        ModelEntity a4 = new ModelEntity()
                .setBrand(audi)
                .setName("A4")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/2018_Audi_A4_Sport_TDi_Quattro_S-A_2.0.jpg/1920px-2018_Audi_A4_Sport_TDi_Quattro_S-A_2.0.jpg")
                .setStartYear(1994);

        ModelEntity accord = new ModelEntity()
                .setBrand(honda)
                .setName("Accord")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg/1920px-Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg")
                .setStartYear(1976);

        ModelEntity m5 = new ModelEntity()
                .setBrand(bmw)
                .setName("550")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://pokupka-globalen.today/pics_MP-Style-Carbon-Fiber-Car-Tail-Wing-For-BMW-M5-F90-1/imgs_80117.jpeg")
                .setStartYear(1972);

        ModelEntity w209 = new ModelEntity()
                .setBrand(mercedes)
                .setName("CLK")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://i.pinimg.com/originals/31/b1/ae/31b1ae3e22489473a65f1209b26d1932.png")
                .setStartYear(2001)
                .setEndYear(2010);

        ModelEntity w223 = new ModelEntity();
        w223.setBrand(mercedes);
        w223.setName("S class");
        w223.setCategory(CategoryEnum.CAR);
        w223.setImageUrl("https://dizzyriders.bg/uploads/thumbs/gallery/2020-09/ed9c441c51b86eea406ec26faea6468d-620x427.jpg");
        w223.setStartYear(2021);

        this.modelRepository.saveAll(List.of(w209, w223, m5, accord, a4, astra));
    }
}
