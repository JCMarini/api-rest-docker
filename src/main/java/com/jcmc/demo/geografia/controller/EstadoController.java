package com.jcmc.demo.geografia.controller;

import com.jcmc.demo.geografia.entity.EstadoEntity;
import com.jcmc.demo.geografia.model.Estado;
import com.jcmc.demo.geografia.service.EstadoService;
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
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    // Crear o actualizar un estado
    @PostMapping
    public ResponseEntity<EstadoEntity> saveEstado(@RequestBody EstadoEntity request) {
        Estado estado = new Estado(request.id_pais(), request.id_estado(),
                request.estado(), request.estatus());

        if (estadoService.saveEstado(estado) != null) {
            estado = estadoService.saveEstado(estado);
            EstadoEntity response =
                    new EstadoEntity(estado.getPais().getIdPais(),
                            estado.getIdEstado(),
                            estado.getEstado(),
                            estado.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    // Crear o actualizar un estado
    @PutMapping
    public ResponseEntity<EstadoEntity> updateEstado(@RequestBody EstadoEntity estadoRequest) {
        Estado estado = new Estado(estadoRequest.id_pais(), estadoRequest.id_estado(),
                estadoRequest.estado(), estadoRequest.estatus());

        if (estadoService.saveEstado(estado) != null) {
            estado = estadoService.saveEstado(estado);
            EstadoEntity response =
                    new EstadoEntity(estado.getPais().getIdPais(),
                            estado.getIdEstado(),
                            estado.getEstado(),
                            estado.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }


    // Obtener todos los estados
    @GetMapping
    public ResponseEntity<List<EstadoEntity>> getEstados() {
        List<EstadoEntity> response = estadoService.getEstados().stream()
                .map(estado -> new EstadoEntity(estado.getPais().getIdPais(),
                        estado.getIdEstado(),
                        estado.getEstado(),
                        estado.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Obtener todos los estados
    @GetMapping("/pais/{id}")
    public ResponseEntity<List<EstadoEntity>> getEstadosByIdPais(@PathVariable Long id) {
        List<EstadoEntity> response = estadoService.getEstadoByIdPais(id).stream()
                .map(estado -> new EstadoEntity(estado.getPais().getIdPais(),
                        estado.getIdEstado(),
                        estado.getEstado(),
                        estado.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }


    // Obtener los estados por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstadoEntity> getEstadoByIdEstado(@PathVariable Long id) {
        Optional<Estado> estado = estadoService.getEstadoByIdEstado(id);
        if (estado.isPresent()) {
            EstadoEntity response =
                    new EstadoEntity(estado.get().getPais().getIdPais(),
                            estado.get().getIdEstado(),
                            estado.get().getEstado(),
                            estado.get().getEstatus());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un estado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        estadoService.deleteEstadoById(id);
        return ResponseEntity.noContent().build();
    }

}
