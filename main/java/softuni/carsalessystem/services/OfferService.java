package softuni.carsalessystem.services;

import softuni.carsalessystem.models.service.OfferAddServiceModel;
import softuni.carsalessystem.models.service.OfferUpdateServiceModel;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.models.view.OfferSummaryView;

import java.util.List;

public interface OfferService {

    void seedOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);

    void delete(Long id);

    void updateOffer(OfferUpdateServiceModel offerModel);

    void addOffer(OfferAddServiceModel offerAddServiceModel);

    List<String> getAllModelsNames();
}
