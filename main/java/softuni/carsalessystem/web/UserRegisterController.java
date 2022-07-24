package softuni.carsalessystem.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.carsalessystem.models.bindings.UserRegisterBindingModel;
import softuni.carsalessystem.models.service.UserRegisterServiceModel;
import softuni.carsalessystem.services.UserService;

@Controller
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/users/register")
    public String registerUser() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(UserRegisterBindingModel userRegisterBindingModel){
        //Todo: Validation

        UserRegisterServiceModel serviceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}
