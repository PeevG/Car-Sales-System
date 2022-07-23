package softuni.carsalessystem.services.impl;

import org.springframework.stereotype.Service;
import softuni.carsalessystem.models.entities.OfferEntity;
import softuni.carsalessystem.models.view.OfferSummaryView;
import softuni.carsalessystem.repositories.OfferRepository;
import softuni.carsalessystem.services.OfferService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void seedOffers() {
        //Todo:
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
       return this.offerRepository.findAll().stream().map(this::map)
                .collect(Collectors.toList());
    }
    private OfferSummaryView map(OfferEntity offerEntity) {
        //ToDo:
        return new OfferSummaryView();
    }
}
