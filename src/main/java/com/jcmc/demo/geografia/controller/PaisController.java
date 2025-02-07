package com.jcmc.demo.geografia.controller;

import com.jcmc.demo.geografia.entity.PaisResponse;
import com.jcmc.demo.geografia.model.Pais;
import com.jcmc.demo.geografia.service.PaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/paises")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    // Crear o actualizar un pais
    @PostMapping
    public ResponseEntity<PaisResponse> savePais(@RequestBody Pais pais) {
        if (paisService.savePais(pais) != null) {
            pais = paisService.savePais(pais);
            PaisResponse response =
                    new PaisResponse(pais.getIdPais(),
                            pais.getPais(),
                            pais.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    // Obtener todos los paises
    @GetMapping
    public ResponseEntity<List<PaisResponse>> getPaises() {
        List<PaisResponse> response = paisService.getPaises().stream()
                .map(pais -> new PaisResponse(pais.getIdPais(),
                        pais.getPais(),
                        pais.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Obtener un pais por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaisResponse> getPaisById(@PathVariable Long id) {
        Optional<Pais> pais = paisService.getPaisById(id);
        if (pais.isPresent()) {
            PaisResponse response =
                    new PaisResponse(pais.get().getIdPais(),
                            pais.get().getPais(),
                            pais.get().getEstatus());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un pais
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        paisService.deleltePaisById(id);
        return ResponseEntity.noContent().build();
    }
}
