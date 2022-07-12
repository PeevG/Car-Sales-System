package softuni.carsalessystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carsalessystem.models.entities.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
