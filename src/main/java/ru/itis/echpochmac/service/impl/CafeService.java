package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.repository.CafeRepository;
import ru.itis.echpochmac.service.ICafeService;

import java.util.List;
import java.util.Optional;

@Service
public class CafeService implements ICafeService {
    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public Optional<Cafe> findCafeByName(String name) {
        return cafeRepository.findCafeByName(name);
    }

    @Override
    public Cafe save(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    @Override
    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }
/*
    @Override
    public Optional<Cafe> findById(Long id) {
        return cafeRepository.findById(id);
    }*/
}
