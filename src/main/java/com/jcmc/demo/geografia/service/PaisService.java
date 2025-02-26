package com.jcmc.demo.geografia.service;

import com.jcmc.demo.auth.model.User;
import com.jcmc.demo.core.util.SessionUtil;
import com.jcmc.demo.geografia.dao.PaisRepository;
import com.jcmc.demo.geografia.model.Pais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaisService {

    private final PaisRepository paisRepository;

    // Crear o actualizar un pais
    public Pais savePais(Pais pais) {
        return paisRepository.savePais(pais);
    }

    public Pais updatePais(Pais pais) {
        User user = SessionUtil.getUser();
        Integer rows = paisRepository.updatePais(pais.getIdPais(),
                pais.getPais(), pais.getEstatus(), user.getIdUsuario());
        if (rows > 0) {
            return pais;
        }
        return null;
    }

    // Obtener todos los paises
    @RateLimiter(name = "limitPaises")
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
