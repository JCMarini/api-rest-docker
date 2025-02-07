package com.jcmc.demo.geografia.service;

import com.jcmc.demo.auth.service.JwtService;
import com.jcmc.demo.geografia.dao.EstadoRespository;
import com.jcmc.demo.geografia.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRespository estadoRespository;
    private JwtService jwtService;

    // Crear o actualizar un estado
    public Estado saveEstado(Estado estado) {
        return estadoRespository.save(estado);
    }

    // Obtener todos los estados
    @Cacheable(value = "getEstados")
    public ArrayList<Estado> getEstados() {
       return new ArrayList<>(estadoRespository.findAll());
    }

    // Obtener un estados por id_pais
    @Cacheable(value = "getEstadoByIdPais")
    public ArrayList<Estado> getEstadoByIdPais(Long id) {
        return new ArrayList<>(estadoRespository.getEstadosByPais(id));
    }

    // Obtener un estados por id_estado
    public Optional<Estado> getEstadoByIdEstado(Long id) {
        return estadoRespository.findById(id);
    }

    // Eliminar un estado por ID
    public void deleteEstadoById(Long id) {
        estadoRespository.deleteById(id);
    }
}
