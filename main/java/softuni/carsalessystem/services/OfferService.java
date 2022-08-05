package softuni.carsalessystem.services;

import softuni.carsalessystem.models.bindings.AddOfferBindingModel;
import softuni.carsalessystem.models.service.OfferAddServiceModel;
import softuni.carsalessystem.models.service.OfferUpdateServiceModel;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.models.view.OfferSummaryView;

import java.security.Principal;
import java.util.List;

public interface OfferService {

    void seedOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);

    void delete(Long id);

    boolean isOwner(String username, Long id);

    void updateOffer(OfferUpdateServiceModel offerModel);

    OfferAddServiceModel addOffer(AddOfferBindingModel addOfferBindingModel, Principal principal);

    List<String> getAllModelsNames();
}
