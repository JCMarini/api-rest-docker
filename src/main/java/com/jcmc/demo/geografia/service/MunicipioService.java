package com.jcmc.demo.geografia.service;

import com.jcmc.demo.geografia.dao.MunicipioRepository;
import com.jcmc.demo.geografia.model.Municipio;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipioService {
    private final MunicipioRepository municipioRepository;

    // Crear o actualizar un estamunicipiodo
    public Municipio saveMunicipio(Municipio municipio) {
        return municipioRepository.saveMunicipio(municipio);
    }

    // Obtener todos los municipio
    @Cacheable(value = "getMunicipios")
    public Page<Municipio> getMunicipios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return municipioRepository.findAll(pageable);
    }

    // Obtener un municipio por id_pais
    @Cacheable(value = "getMunicipiosByEstadoId")
    public ArrayList<Municipio> getMunicipiosByEstadoId(Long id) {
        return new ArrayList<>(municipioRepository.getMunicipiosByEstadoId(id));
    }

    // Obtener un municipio por id_estado
    public Optional<Municipio> getMunicipioById(Long id) {
        return municipioRepository.findById(id);
    }

    // Eliminar un municipio por ID
    public void deleteMunicipioById(Long id) {
        municipioRepository.deleteById(id);
    }
}
