package softuni.carsalessystem.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carsalessystem.repositories.BrandRepository;
import softuni.carsalessystem.enums.CategoryEnum;
import softuni.carsalessystem.models.entities.BrandEntity;
import softuni.carsalessystem.models.entities.ModelEntity;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;

    public DBInit(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDB();
    }

    private void seedDB() {
        if (brandRepository.count() > 0) {
            return;
        }
        BrandEntity mercedes = new BrandEntity();
        mercedes.setName("Mercedes");

        ModelEntity w209 = new ModelEntity()
                .setName("CLK")
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://i.pinimg.com/originals/31/b1/ae/31b1ae3e22489473a65f1209b26d1932.png")
                .setStartYear(2001)
                .setEndYear(2010);

        ModelEntity w223 = new ModelEntity();
        w223.setName("S class");
        w223.setCategory(CategoryEnum.CAR);
        w223.setImageUrl("https://dizzyriders.bg/uploads/thumbs/gallery/2020-09/ed9c441c51b86eea406ec26faea6468d-620x427.jpg");
        w223.setStartYear(2021);

        mercedes.setModels(List.of(w209, w223));
        w209.setBrand(mercedes);
        w223.setBrand(mercedes);
        this.brandRepository.save(mercedes);
    }
}
