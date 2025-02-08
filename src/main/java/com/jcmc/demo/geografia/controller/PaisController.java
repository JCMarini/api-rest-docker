package com.jcmc.demo.geografia.controller;

import com.jcmc.demo.geografia.entity.PaisEntity;
import com.jcmc.demo.geografia.model.Pais;
import com.jcmc.demo.geografia.service.PaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Crear  un pais
    @PostMapping
    public ResponseEntity<PaisEntity> savePais(@RequestBody PaisEntity request) {
        Pais pais = new Pais(request.id_pais(), request.pais(), request.estatus());
        pais = paisService.savePais(pais);
        PaisEntity response =
                new PaisEntity(pais.getIdPais(),
                        pais.getPais(),
                        pais.getEstatus());
        return ResponseEntity.ok(response);
    }

    // Actualizar un pais
    @PutMapping
    public ResponseEntity<PaisEntity> updatePais(@RequestBody PaisEntity request) {
        Pais pais = new Pais(request.id_pais(), request.pais(), request.estatus());
        pais = paisService.updatePais(pais);
        PaisEntity response =
                new PaisEntity(pais.getIdPais(),
                        pais.getPais(),
                        pais.getEstatus());
        return ResponseEntity.ok(response);
    }

    // Obtener todos los paises
    @GetMapping
    public ResponseEntity<List<PaisEntity>> getPaises() {
        List<PaisEntity> response = paisService.getPaises().stream()
                .map(pais -> new PaisEntity(pais.getIdPais(),
                        pais.getPais(),
                        pais.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Obtener un pais por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaisEntity> getPaisById(@PathVariable Long id) {
        Optional<Pais> pais = paisService.getPaisById(id);
        if (pais.isPresent()) {
            PaisEntity response =
                    new PaisEntity(pais.get().getIdPais(),
                            pais.get().getPais(),
                            pais.get().getEstatus());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un pais
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePais(@PathVariable Long id) {
        paisService.deleltePaisById(id);
        return ResponseEntity.ok("El pais se borro de manera correcta");
    }
}
