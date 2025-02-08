package com.jcmc.demo.geografia.controller;

import com.jcmc.demo.geografia.entity.MunicipioEntity;
import com.jcmc.demo.geografia.model.Municipio;
import com.jcmc.demo.geografia.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    // Crear un municipio
    @PostMapping
    public ResponseEntity<MunicipioEntity> saveMunicipio(@RequestBody MunicipioEntity request) {
        Municipio municipio =
                new Municipio(request.id_pais(), request.id_estado(),
                        request.id_municipio(), request.municipio(), request.estatus());

        if (municipioService.saveMunicipio(municipio) != null) {
            municipio = municipioService.saveMunicipio(municipio);
            MunicipioEntity response =
                    new MunicipioEntity(municipio.getPais().getIdPais(),
                            municipio.getEstado().getIdEstado(),
                            municipio.getIdMunicipio(),
                            municipio.getMunicipio(),
                            municipio.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    // Actualizar un municipio
    @PutMapping
    public ResponseEntity<MunicipioEntity> updateMunicipio(@RequestBody MunicipioEntity request) {
        Municipio municipio =
                new Municipio(request.id_pais(), request.id_estado(),
                        request.id_municipio(), request.municipio(), request.estatus());

        if (municipioService.saveMunicipio(municipio) != null) {
            municipio = municipioService.saveMunicipio(municipio);
            MunicipioEntity response =
                    new MunicipioEntity(municipio.getPais().getIdPais(),
                            municipio.getEstado().getIdEstado(),
                            municipio.getIdMunicipio(),
                            municipio.getMunicipio(),
                            municipio.getEstatus());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    // Obtener todos los municipio Usando paginas
    @GetMapping()
    public ResponseEntity<List<MunicipioEntity>> getMunicipio(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        List<MunicipioEntity> response = municipioService.getMunicipios(page, size).stream()
                .map(municipio -> new MunicipioEntity(municipio.getPais().getIdPais(),
                        municipio.getEstado().getIdEstado(),
                        municipio.getIdMunicipio(),
                        municipio.getMunicipio(),
                        municipio.getEstatus())).toList();

        return ResponseEntity.ok(response);
    }

    // Obtener todos los municipio de un estado
    @GetMapping("/estado/{id}")
    public ResponseEntity<List<MunicipioEntity>> getMunicipioByIdEstado(@PathVariable Long id) {
        List<MunicipioEntity> response = municipioService.getMunicipiosByEstadoId(id).stream()
                .map(municipio -> new MunicipioEntity(municipio.getPais().getIdPais(),
                        municipio.getEstado().getIdEstado(),
                        municipio.getIdMunicipio(),
                        municipio.getMunicipio(),
                        municipio.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }


    // Obtener los municipio por ID
    @GetMapping("/{id}")
    public ResponseEntity<MunicipioEntity> getMunicipioById(@PathVariable Long id) {
        Optional<Municipio> municipio = municipioService.getMunicipioById(id);
        if (municipio.isPresent()) {
            MunicipioEntity response =
                    new MunicipioEntity(municipio.get().getPais().getIdPais(),
                            municipio.get().getEstado().getIdEstado(),
                            municipio.get().getIdMunicipio(),
                            municipio.get().getMunicipio(),
                            municipio.get().getEstatus());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un municipio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMunicipio(@PathVariable Long id) {
        municipioService.deleteMunicipioById(id);
        return ResponseEntity.noContent().build();
    }
}
