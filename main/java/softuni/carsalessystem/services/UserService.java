package softuni.carsalessystem.services;

import softuni.carsalessystem.models.dto.UserLoginDTO;

public interface UserService {
    boolean login(UserLoginDTO userLoginDTO);
}
