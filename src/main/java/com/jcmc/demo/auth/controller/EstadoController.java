package com.jcmc.demo.auth.controller;

import com.jcmc.demo.auth.entity.EstadoResponse;
import com.jcmc.demo.auth.model.Estado;
import com.jcmc.demo.auth.service.EstadoService;
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
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    // Crear o actualizar un estado
    @PostMapping
    public ResponseEntity<EstadoResponse> saveEstado(@RequestBody Estado estado) {
        if (estadoService.saveEstado(estado) != null) {
            estado = estadoService.saveEstado(estado);
            EstadoResponse response =
                    new EstadoResponse(estado.getPais().getIdPais(),
                            estado.getIdEstado(), estado.getEstado(),
                            estado.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    // Obtener todos los estados
    @GetMapping
    public ResponseEntity<List<EstadoResponse>> getEstados() {
        List<EstadoResponse> response = estadoService.getEstados().stream()
                .map(estado -> new EstadoResponse(estado.getPais().getIdPais(), estado.getIdEstado(),
                        estado.getEstado(), estado.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Obtener todos los estados
    @GetMapping("/pais/{id}")
    public ResponseEntity<List<EstadoResponse>> getEstadosByIdPais(@PathVariable Long id) {
        List<EstadoResponse> response = estadoService.getEstadoByIdPais(id).stream()
                .map(estado -> new EstadoResponse(estado.getPais().getIdPais(), estado.getIdEstado(),
                        estado.getEstado(), estado.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }


    // Obtener los estados por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponse> getEstadoByIdEstado(@PathVariable Long id) {
        Optional<Estado> estado = estadoService.getEstadoByIdEstado(id);
        if (estado.isPresent()) {
            EstadoResponse response =
                    new EstadoResponse(estado.get().getPais().getIdPais(), estado.get().getIdEstado(),
                            estado.get().getEstado(), estado.get().getEstatus());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un estado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        estadoService.delelteEstadoById(id);
        return ResponseEntity.noContent().build();
    }

}
