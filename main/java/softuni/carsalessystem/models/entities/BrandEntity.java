package softuni.carsalessystem.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;


    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
