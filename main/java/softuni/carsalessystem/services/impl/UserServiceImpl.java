package softuni.carsalessystem.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.bindings.UserLoginBindingModel;
import softuni.carsalessystem.models.entities.UserEntity;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.services.UserService;
import softuni.carsalessystem.user.CurrentUser;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (userEntityOptional.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder
                    .matches(userLoginBindingModel
                            .getPassword(), userEntityOptional.get().getPassword());

            if (success) {
                UserEntity loggedInUser = userEntityOptional.get();
                currentUser.setLoggedIn(true);
                currentUser.setUsername(loggedInUser.getUsername());
                currentUser.setFirstName(loggedInUser.getFirstName());
                currentUser.setLastName(loggedInUser.getLastName());
            }
            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.cleanUserInfo();
    }
}
