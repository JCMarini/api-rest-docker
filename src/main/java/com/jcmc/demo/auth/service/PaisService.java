package com.jcmc.demo.auth.service;

import com.jcmc.demo.auth.dao.PaisRepository;
import com.jcmc.demo.auth.model.Pais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaisService {

    private final PaisRepository paisRepository;

    // Crear o actualizar un pais
    public Pais savePais(Pais pais) {
        return paisRepository.save(pais);
    }

    // Obtener todos los paises
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }

    // Obtener un pais por ID
    public Optional<Pais> getPaisById(Long id) {
        return paisRepository.findById(id);
    }

    // Eliminar un pais por ID
    public void deleltePaisById(Long id) {
        paisRepository.deleteById(id);
    }
}
