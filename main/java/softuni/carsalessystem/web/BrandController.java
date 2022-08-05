package softuni.carsalessystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.carsalessystem.services.BrandService;

@Controller
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/all")
    public String showBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brands";
    }
}
