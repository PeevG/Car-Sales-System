package softuni.carsalessystem.services;

import softuni.carsalessystem.models.bindings.UserLoginBindingModel;

public interface UserService {
    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
