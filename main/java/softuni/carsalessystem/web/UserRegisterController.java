package softuni.carsalessystem.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carsalessystem.models.bindings.UserRegisterBindingModel;
import softuni.carsalessystem.models.service.UserRegisterServiceModel;
import softuni.carsalessystem.services.UserService;

import javax.validation.Valid;

@Controller
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel userModel(){
        return new UserRegisterBindingModel();
    }


    @GetMapping("/users/register")
    public String registerUser() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterBindingModel userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel"
                    , bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel serviceModel = modelMapper.map(userModel, UserRegisterServiceModel.class);

        if(!userService.isUserNameFree(serviceModel.getUsername())){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("userNameOccupied", true);
            return "redirect:/users/register";
        } else {
            userService.registerAndLoginUser(serviceModel);
        }

        return "redirect:/";
    }
}
