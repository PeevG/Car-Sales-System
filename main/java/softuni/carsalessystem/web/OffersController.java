package softuni.carsalessystem.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carsalessystem.enums.EngineEnum;
import softuni.carsalessystem.enums.TransmissionEnum;
import softuni.carsalessystem.models.bindings.AddOfferBindingModel;
import softuni.carsalessystem.models.bindings.OfferUpdateBindingModel;
import softuni.carsalessystem.models.service.OfferAddServiceModel;
import softuni.carsalessystem.models.service.OfferUpdateServiceModel;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.services.BrandService;
import softuni.carsalessystem.services.OfferService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;


    public OffersController(OfferService offerService, ModelMapper modelMapper, BrandService brandService) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model) {
        model.addAttribute("offer", this.offerService.findById(id));
        return "details";
    }

    @PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id,
                              Principal principal) {
        this.offerService.delete(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {
        OfferDetailsView offerDetailsView = offerService.findById(id);

        OfferUpdateBindingModel offerModel = modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("offerModel", offerModel);
        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid OfferUpdateBindingModel offerModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferUpdateServiceModel serviceModel = modelMapper.map(offerModel, OfferUpdateServiceModel.class);
        serviceModel.setId(id);

        offerService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";
    }


    @GetMapping("/offers/add")
    public String addOffer(Model model) {


        if (!model.containsAttribute("addOfferBindingModel")) {
            model.addAttribute("addOfferBindingModel", new AddOfferBindingModel());
            model.addAttribute("brands", this.brandService.getAllBrands());
            model.addAttribute("engines", EngineEnum.values());
            model.addAttribute("transmissions", TransmissionEnum.values());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid AddOfferBindingModel addOfferBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferBindingModel", addOfferBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("engines", EngineEnum.values());
           redirectAttributes.addFlashAttribute("transmissions", TransmissionEnum.values());

            return "redirect:/offers/add";
        }

        OfferAddServiceModel serviceModel = offerService.addOffer(addOfferBindingModel, principal);

        return "redirect:/offers/" + serviceModel.getId() + "/details";
    }
}
