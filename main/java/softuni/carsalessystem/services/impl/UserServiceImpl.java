package softuni.carsalessystem.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.enums.UserRoleEnum;
import softuni.carsalessystem.models.bindings.UserLoginBindingModel;
import softuni.carsalessystem.models.entities.UserEntity;
import softuni.carsalessystem.models.entities.UserRoleEntity;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.repositories.UserRoleRepository;
import softuni.carsalessystem.services.UserService;
import softuni.carsalessystem.user.CurrentUser;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.userRoleRepository = userRoleRepository;
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

                loggedInUser.getRoles()
                        .forEach(r -> currentUser.addRole(r.getRole()));
            }
            return success;
        }
    }


    @Override
    public void logout() {
        currentUser.cleanUserInfo();
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity admin = new UserEntity();
        admin.setUsername("Admin");
        admin.setPassword(passwordEncoder.encode("test"));
        admin.setFirstName("Georgi");
        admin.setLastName("Petrov");
        admin.setActive(true);
        admin.setRoles(List.of(adminRole, userRole));
        userRepository.save(admin);

        UserEntity user = new UserEntity();
        user.setUsername("Martin");
        user.setPassword(passwordEncoder.encode("Martinov"));
        user.setActive(true);
        user.setFirstName("Martin");
        user.setLastName("Ivanov");
        user.setRoles(List.of(userRole));
        userRepository.save(user);
    }

    @Override
    public void seedUserRoles() {
        if (userRoleRepository.count() > 0) {
            return;
        }
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);
    }
}

