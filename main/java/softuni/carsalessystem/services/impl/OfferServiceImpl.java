package softuni.carsalessystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.carsalessystem.enums.EngineEnum;
import softuni.carsalessystem.enums.TransmissionEnum;
import softuni.carsalessystem.models.bindings.AddOfferBindingModel;
import softuni.carsalessystem.models.entities.ModelEntity;
import softuni.carsalessystem.models.entities.OfferEntity;
import softuni.carsalessystem.models.service.OfferAddServiceModel;
import softuni.carsalessystem.models.service.OfferUpdateServiceModel;
import softuni.carsalessystem.models.view.OfferDetailsView;
import softuni.carsalessystem.models.view.OfferSummaryView;
import softuni.carsalessystem.repositories.ModelRepository;
import softuni.carsalessystem.repositories.OfferRepository;
import softuni.carsalessystem.repositories.UserRepository;
import softuni.carsalessystem.services.OfferService;
import softuni.carsalessystem.web.exception.ObjectNotFoundException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
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
        if (offerRepository.count() > 0) {
            return;
        }

        OfferEntity offer1 = new OfferEntity();
        offer1.setModel(modelRepository.findByName("CLK").orElse(null));
        offer1.setEngine(EngineEnum.DIESEL);
        offer1.setTransmission(TransmissionEnum.AUTOMATIC);
        offer1.setMileage(215000);
        offer1.setPrice(BigDecimal.valueOf(11200));
        offer1.setYear(2004);
        offer1.setDescription("Always serviced on time and in good condition.");
        offer1.setSeller(userRepository.findByUsername("Martin").orElse(null));
        offer1.setImageUrl("https://cloud.leparking.fr/2021/08/04/01/27/mercedes-clk-mercedes-benz-w209-clk-320-grau_8227762968.jpg");

        OfferEntity offer2 = new OfferEntity();
        offer2.setModel(modelRepository.findByName("S class").orElse(null));
        offer2.setEngine(EngineEnum.GASOLINE);
        offer2.setTransmission(TransmissionEnum.AUTOMATIC);
        offer2.setMileage(125000);
        offer2.setPrice(BigDecimal.valueOf(30200));
        offer2.setYear(2012);
        offer2.setDescription("Always serviced on time and in good condition.");
        offer2.setSeller(userRepository.findByUsername("Admin").orElse(null));
        offer2.setImageUrl("https://s1.cdn.autoevolution.com/images/gallery/MERCEDES-BENZ-S-Klasse--W221--4131_31.jpg");


        OfferEntity offer3 = new OfferEntity()
                .setModel(modelRepository.findByName("550").orElse(null))
                .setEngine(EngineEnum.GASOLINE)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(50000)
                .setPrice(BigDecimal.valueOf(120000))
                .setYear(2019)
                .setDescription("The car is under warranty until 2024.")
                .setSeller(userRepository.findByUsername("Admin").orElse(null))
                .setImageUrl("https://pokupka-globalen.today/pics_MP-Style-Carbon-Fiber-Car-Tail-Wing-For-BMW-M5-F90-1/imgs_80117.jpeg");

        OfferEntity offer4 = new OfferEntity()
                .setModel(modelRepository.findByName("Accord").orElse(null))
                .setEngine(EngineEnum.DIESEL)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(75000)
                .setPrice(BigDecimal.valueOf(60000))
                .setYear(2017)
                .setDescription("Always serviced on time and in good condition.")
                .setSeller(userRepository.findByUsername("Admin").orElse(null))
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg/1920px-Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg");

        OfferEntity offer5 = new OfferEntity()
                .setModel(modelRepository.findByName("A4").orElse(null))
                .setEngine(EngineEnum.HYBRID)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(70000)
                .setPrice(BigDecimal.valueOf(50000))
                .setYear(2018)
                .setDescription("Always serviced on time and in good condition.")
                .setSeller(userRepository.findByUsername("Martin").orElse(null))
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/2018_Audi_A4_Sport_TDi_Quattro_S-A_2.0.jpg/1920px-2018_Audi_A4_Sport_TDi_Quattro_S-A_2.0.jpg");

        OfferEntity offer6 = new OfferEntity()
                .setModel(modelRepository.findByName("Astra").orElse(null))
                .setEngine(EngineEnum.ELECTRIC)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(20000)
                .setPrice(BigDecimal.valueOf(50000))
                .setYear(2021)
                .setDescription("The car is under warranty until 2026.")
                .setSeller(userRepository.findByUsername("Martin").orElse(null))
                .setImageUrl("https://automedia.investor.bg/media/files/resized/gallery/760x/248/c1b964dacd29eedf51d157d6f45c7248-01-12.jpg");

        offerRepository.saveAll(List.of(offer1, offer2, offer3, offer4, offer5, offer6));
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


    @Override
    public void updateOffer(OfferUpdateServiceModel offerModel) {
        OfferEntity offerEntity = offerRepository.findById(offerModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + offerModel.getId() + " not found!"));

        offerEntity.setPrice(offerModel.getPrice())
                .setDescription(offerModel.getDescription())
                .setEngine(offerModel.getEngine())
                .setImageUrl(offerModel.getImageUrl())
                .setMileage(offerModel.getMileage())
                .setTransmission(offerModel.getTransmission())
                .setYear(offerModel.getYear())
                .setModified(Instant.now());

        offerRepository.save(offerEntity);
    }

    @Override
    public OfferAddServiceModel addOffer(AddOfferBindingModel addOfferBindingModel, Principal principal) {

        OfferAddServiceModel offerAddServiceModel = modelMapper.map(addOfferBindingModel, OfferAddServiceModel.class);
        OfferEntity newOffer = modelMapper.map(offerAddServiceModel, OfferEntity.class);

        newOffer
                //.setEngine(addOfferBindingModel.getEngine())
                .setSeller(userRepository.findByUsername(principal.getName()).orElseThrow())
                .setModel(modelRepository.findById(addOfferBindingModel.getId()).orElse(null))
               // .setDescription(addOfferBindingModel.getDescription())
                .setPrice(BigDecimal.valueOf(addOfferBindingModel.getPrice()))
                .setTransmission(addOfferBindingModel.getTransmission())
                .setYear(addOfferBindingModel.getYear())
                .setMileage(addOfferBindingModel.getMileage())
                .setImageUrl(addOfferBindingModel.getImageUrl())
                .setCreated(Instant.now());

        OfferEntity savedOffer = this.offerRepository.save(newOffer);
        return modelMapper.map(savedOffer, OfferAddServiceModel.class);
    }

    @Override
    public List<String> getAllModelsNames() {
        return modelRepository.findAll()
                .stream()
                .map(ModelEntity::getName)
                .collect(Collectors.toList());
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

        offerSummaryView.setYear(offerEntity.getYear());
        offerSummaryView.setModel(offerEntity.getModel().getName());
        offerSummaryView.setBrand(offerEntity.getModel().getBrand().getName());

        return offerSummaryView;
    }
}
