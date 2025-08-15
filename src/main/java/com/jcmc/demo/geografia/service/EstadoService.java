package com.jcmc.demo.geografia.service;

import com.jcmc.demo.auth.service.JwtService;
import com.jcmc.demo.core.util.Logger;
import com.jcmc.demo.geografia.dao.EstadoRespository;
import com.jcmc.demo.geografia.model.Estado;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private static final Logger logger = Logger.getLogger(EstadoService.class);

    private final EstadoRespository estadoRespository;
    private JwtService jwtService;

    // Crear o actualizar un estado
    @RateLimiter(name = "limitEstados")
    public Estado saveEstado(Estado estado) {
        logger.info("Se guado un nuevo estado : ");
        return estadoRespository.saveEstado(estado);
    }

    // Obtener todos los estados
    @Cacheable(value = "getEstados")
    @RateLimiter(name = "limitEstados")
    public ArrayList<Estado> getEstados() {
        logger.info("Se regresaron todos los estados");
        return new ArrayList<>(estadoRespository.findAll());
    }

    // Obtener un estados por id_pais
    @Cacheable(value = "getEstadoByIdPais")
    public ArrayList<Estado> getEstadoByIdPais(Long id) {
        logger.info("Se obtuvieron todos los estados del pais : " + id);
        return new ArrayList<>(estadoRespository.getEstadosByPais(id));
    }

    // Obtener un estados por id_estado
    public Optional<Estado> getEstadoByIdEstado(Long id) {
        logger.info("Se obtuvo el estado con id : " + id);
        return estadoRespository.findById(id);
    }

    // Eliminar un estado por ID
    public void deleteEstadoById(Long id) {
        logger.info("Se elimino el estado con id : " + id);
        estadoRespository.deleteById(id);
    }
}
