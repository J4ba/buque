package com.CordyTech.Buque.service;

import com.CordyTech.Buque.model.Buque;
import com.CordyTech.Buque.repository.BuqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuqueService {

    @Autowired
    private BuqueRepository buqueRepository;

    // Crear o actualizar
    public Buque saveBuque(Buque buque) {
        return buqueRepository.save(buque);
    }

    // Obtener todos
    public List<Buque> getAllBuques() {
        return buqueRepository.findAll();
    }

    // Obtener por ID
    public Optional<Buque> getBuqueById(String codBuque) {
        return buqueRepository.findById(codBuque);
    }

    // Eliminar
    public void deleteBuque(String codBuque) {
        buqueRepository.deleteById(codBuque);
    }
}

