package softuni.carsalessystem.web;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.carsalessystem.models.dto.UserLoginDTO;
import softuni.carsalessystem.services.UserService;

@Controller
public class UserLoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO) {


        UserLoginDTO dto = new UserLoginDTO();
        dto.setUsername(userLoginDTO.getUsername());
        dto.setPassword(userLoginDTO.getPassword());
        boolean isLogged = userService.login(dto);

        LOGGER.info("User tried to login. User with name {} tried to login. Success = {}", userLoginDTO.getPassword()
                ,isLogged);

        return "redirect:/users/login";
    }
}
