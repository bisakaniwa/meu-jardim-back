package br.com.meujardim.repository;

import br.com.meujardim.model.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantaRepository extends JpaRepository<Planta, Long> {
}
