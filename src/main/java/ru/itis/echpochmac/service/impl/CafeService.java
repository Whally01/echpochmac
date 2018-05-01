package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.repository.CafeRepository;
import ru.itis.echpochmac.service.ICafeService;

import java.util.Optional;

@Service
public class CafeService implements ICafeService {
    @Autowired
    private CafeRepository cafeRepository;

    @Override
    public Optional<Cafe> findCafeByName(String name) {
        return cafeRepository.findCafeByName(name);
    }

    @Override
    public Cafe save(Cafe cafe) {
        return cafeRepository.save(cafe);
    }
}
