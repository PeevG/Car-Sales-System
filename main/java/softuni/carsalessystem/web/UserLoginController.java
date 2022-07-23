package softuni.carsalessystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.carsalessystem.models.bindings.UserLoginBindingModel;
import softuni.carsalessystem.services.UserService;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {

        UserLoginBindingModel dto = new UserLoginBindingModel();
        dto.setUsername(userLoginBindingModel.getUsername());
        dto.setPassword(userLoginBindingModel.getPassword());

        boolean isLogged = userService.login(dto);

        LOGGER.info("User tried to login. User with name {} tried to login. Success = {}", userLoginBindingModel.getPassword()
                ,isLogged);

        if(isLogged) {
            return "redirect:/";
        }

        return "redirect:/users/login";
    }
}
