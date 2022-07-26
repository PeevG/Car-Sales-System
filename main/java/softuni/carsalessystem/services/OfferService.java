package softuni.carsalessystem.services;

import softuni.carsalessystem.models.entities.OfferEntity;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.models.view.OfferSummaryView;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void seedOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);

    void delete(Long id);
}
