package softuni.carsalessystem.services;

import softuni.carsalessystem.models.view.OfferSummaryView;

import java.util.List;

public interface OfferService {

    void seedOffers();

    List<OfferSummaryView> getAllOffers();
}
