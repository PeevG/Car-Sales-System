package softuni.carsalessystem.services;

import softuni.carsalessystem.models.bindings.UserLoginBindingModel;
import softuni.carsalessystem.models.service.UserRegisterServiceModel;

public interface UserService {

    void seedUsers();
    void seedUserRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUserNameFree(String username);
}
