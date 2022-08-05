package softuni.carsalessystem.services.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.enums.UserRoleEnum;
import softuni.carsalessystem.models.entities.UserEntity;
import softuni.carsalessystem.models.entities.UserRoleEntity;
import softuni.carsalessystem.models.service.UserRegisterServiceModel;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.repositories.UserRoleRepository;
import softuni.carsalessystem.services.UserService;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final AppUserServiceImpl appUserService;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, AppUserServiceImpl appUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.appUserService = appUserService;
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

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        userRole.setRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userRegisterServiceModel.getUsername());
        newUser.setFirstName(userRegisterServiceModel.getFirstName());
        newUser.setLastName(userRegisterServiceModel.getLastName());
        newUser.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        newUser.setActive(true);
        newUser.setRoles(List.of(userRole));

        newUser = userRepository.save(newUser);
        UserDetails principal = appUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, newUser.getPassword(), principal.getAuthorities());

        authentication.getAuthorities();

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .isEmpty();
    }

}

