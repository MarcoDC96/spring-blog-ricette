package org.learning.springbot.repository;

import org.learning.springbot.model.Ricette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicetteRepository extends JpaRepository<Ricette, Integer> {
}
