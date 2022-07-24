package softuni.carsalessystem.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carsalessystem.services.BrandService;
import softuni.carsalessystem.services.ModelService;
import softuni.carsalessystem.services.OfferService;
import softuni.carsalessystem.services.UserService;

@Component
public class DBInit implements CommandLineRunner {


    private final BrandService brandService;
    private final UserService userService;
    private final ModelService modelService;
    private final OfferService offerService;

    public DBInit(BrandService brandService,
                  UserService userService, ModelService modelService, OfferService offerService) {

        this.brandService = brandService;
        this.userService = userService;
        this.modelService = modelService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        brandService.seedBrands();
        modelService.seedModels();
        userService.seedUserRoles();
        userService.seedUsers();
        offerService.seedOffers();
    }


}
