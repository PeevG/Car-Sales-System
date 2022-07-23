package softuni.carsalessystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import softuni.carsalessystem.models.entities.ModelEntity;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}
