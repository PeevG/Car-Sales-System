package softuni.carsalessystem.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.carsalessystem.enums.UserRoleEnum;
import softuni.carsalessystem.models.entities.UserRoleEntity;
import softuni.carsalessystem.repositories.UserRoleRepository;
import softuni.carsalessystem.services.BrandService;
import softuni.carsalessystem.services.ModelService;
import softuni.carsalessystem.services.UserService;

@Component
public class DBInit implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final BrandService brandService;
    private final UserService userService;
    private final ModelService modelService;

    public DBInit(PasswordEncoder passwordEncoder, BrandService brandService,
                  UserService userService, ModelService modelService) {
        this.passwordEncoder = passwordEncoder;
        this.brandService = brandService;
        this.userService = userService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) throws Exception {
        brandService.seedBrands();
        modelService.seedModels();
        userService.seedUserRoles();
        userService.seedUsers();
    }


}
