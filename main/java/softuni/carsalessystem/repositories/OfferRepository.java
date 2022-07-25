package softuni.carsalessystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carsalessystem.models.entities.OfferEntity;
import softuni.carsalessystem.models.view.OfferSummaryView;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    Optional<OfferEntity> findById(Long id);
}
