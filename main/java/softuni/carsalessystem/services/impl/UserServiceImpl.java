package softuni.carsalessystem.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.dto.UserLoginDTO;
import softuni.carsalessystem.models.entities.UserEntity;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userLoginDTO.getUsername());

        if (userEntityOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(userLoginDTO.getPassword(), userEntityOptional.get().getPassword());
        }
    }
}
