package softuni.carsalessystem.models.bindings;

import softuni.carsalessystem.models.entities.ModelEntity;

import java.util.List;

public class BrandDto {

    private String name;
    private List<ModelEntity> models;

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandDto setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}
