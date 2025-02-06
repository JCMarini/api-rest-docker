package com.jcmc.demo.auth.service;

import com.jcmc.demo.auth.dao.EstadoRespository;
import com.jcmc.demo.auth.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRespository estadoRespository;

    // Crear o actualizar un estado
    public Estado saveEstado(Estado estado) {
        return estadoRespository.save(estado);
    }

    // Obtener todos los estados
    public List<Estado> getEstados() {
        return estadoRespository.findAll();
    }

    // Obtener un estados por id_pais
    public List<Estado> getEstadoByIdPais(Long id) {
        return estadoRespository.getEstadosByPais(id);
    }

    // Obtener un estados por id_estado
    public Optional<Estado> getEstadoByIdEstado(Long id) {
        return estadoRespository.findById(id);
    }


    // Eliminar un pais por ID
    public void delelteEstadoById(Long id) {
        estadoRespository.deleteById(id);
    }
}
