package softuni.carsalessystem.models.bindings;

import softuni.carsalessystem.enums.EngineEnum;
import softuni.carsalessystem.enums.TransmissionEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class AddOfferBindingModel {

    private Long id;
    private String model;
    private Integer price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private Integer year;
    private Integer mileage;
    private String description;
    private String imageUrl;

    public AddOfferBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public AddOfferBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getModel() {
        return model;
    }

    public AddOfferBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    @Positive
    public Integer getPrice() {
        return price;
    }

    public AddOfferBindingModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @NotNull
    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    @NotNull
    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    @NotNull
    public Integer getYear() {
        return year;
    }

    public AddOfferBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    @PositiveOrZero
    public Integer getMileage() {
        return mileage;
    }

    public AddOfferBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public AddOfferBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
