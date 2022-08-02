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
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

}
