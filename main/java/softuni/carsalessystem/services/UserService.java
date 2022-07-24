package softuni.carsalessystem.services;

import softuni.carsalessystem.models.bindings.UserLoginBindingModel;
import softuni.carsalessystem.models.service.UserRegisterServiceModel;

public interface UserService {
    boolean login(UserLoginBindingModel userLoginBindingModel);
    void logout();
    void seedUsers();
    void seedUserRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
}
