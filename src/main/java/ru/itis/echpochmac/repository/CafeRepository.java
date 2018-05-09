package ru.itis.echpochmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Cafe;

import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    Optional<Cafe> findCafeByName(String name);
    //Optional<Cafe> findById(Long id);
}
