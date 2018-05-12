package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.User;

import java.util.List;
import java.util.Optional;

public interface ICafeService {
    Optional<Cafe> findCafeByName(String name);

    Optional<Cafe> findById(String cafeId);

    Cafe save(Cafe cafe);

    List<Cafe> findAll();

  /*  Optional<Cafe> findById(Long id);*/

}
