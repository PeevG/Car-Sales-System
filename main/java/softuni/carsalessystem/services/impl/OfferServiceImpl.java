package softuni.carsalessystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.enums.EngineEnum;
import softuni.carsalessystem.enums.TransmissionEnum;
import softuni.carsalessystem.models.entities.OfferEntity;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.models.view.OfferSummaryView;
import softuni.carsalessystem.repositories.ModelRepository;
import softuni.carsalessystem.repositories.OfferRepository;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.services.OfferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedOffers() {
        if(offerRepository.count() > 0) {
            return;
        }
        OfferEntity ofer1 = new OfferEntity();
        ofer1.setModel(modelRepository.findByName("CLK").orElse(null));
        ofer1.setEngine(EngineEnum.DIESEL);
        ofer1.setTransmission(TransmissionEnum.AUTOMATIC);
        ofer1.setMileage(215000);
        ofer1.setPrice(BigDecimal.valueOf(11200));
        ofer1.setYear(2004);
        ofer1.setDescription("Always serviced on time and in good condition.");
        ofer1.setSeller(userRepository.findByUsername("Martin").orElse(null));
        ofer1.setImageUrl("https://cloud.leparking.fr/2021/08/04/01/27/mercedes-clk-mercedes-benz-w209-clk-320-grau_8227762968.jpg");

        OfferEntity ofer2 = new OfferEntity();
        ofer2.setModel(modelRepository.findByName("S class").orElse(null));
        ofer2.setEngine(EngineEnum.GASOLINE);
        ofer2.setTransmission(TransmissionEnum.AUTOMATIC);
        ofer2.setMileage(125000);
        ofer2.setPrice(BigDecimal.valueOf(30200));
        ofer2.setYear(2012);
        ofer2.setDescription("Always serviced on time and in good condition.");
        ofer2.setSeller(userRepository.findByUsername("Admin").orElse(null));
        ofer2.setImageUrl("https://s1.cdn.autoevolution.com/images/gallery/MERCEDES-BENZ-S-Klasse--W221--4131_31.jpg");

        offerRepository.saveAll(List.of(ofer1, ofer2));
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
       return this.offerRepository
               .findAll()
               .stream()
               .map(this::mapSummaryView)
               .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsView findById(Long id) {
        return this.offerRepository
                .findById(id)
                .map(this::mapDetailsView)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.offerRepository.deleteById(id);
    }

    private OfferDetailsView mapDetailsView(OfferEntity offerEntity) {
        OfferDetailsView odv = this.modelMapper.map(offerEntity, OfferDetailsView.class);
        odv.setBrand(offerEntity.getModel().getBrand().getName());
        odv.setModel(offerEntity.getModel().getName());
        odv.setFirstName(offerEntity.getSeller().getFirstName());
        odv.setLastName(offerEntity.getSeller().getLastName());
        return odv;
    }

    private OfferSummaryView mapSummaryView(OfferEntity offerEntity) {

        OfferSummaryView offerSummaryView = modelMapper.map(offerEntity, OfferSummaryView.class);

        offerSummaryView.setModel(offerEntity.getModel().getName());

        return offerSummaryView;
    }
}
