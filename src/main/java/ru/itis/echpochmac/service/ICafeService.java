package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Cafe;

import java.util.Optional;

public interface ICafeService {
    Optional<Cafe> findCafeByName(String name);
    Cafe save(Cafe cafe);

}
