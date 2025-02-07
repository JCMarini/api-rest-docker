package com.jcmc.demo.geografia.controller;

import com.jcmc.demo.geografia.entity.MunicipioRequest;
import com.jcmc.demo.geografia.entity.MunicipioResponse;
import com.jcmc.demo.geografia.model.Municipio;
import com.jcmc.demo.geografia.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Crear o actualizar un municipio
    @PostMapping
    public ResponseEntity<MunicipioResponse> saveMunicipio(@RequestBody MunicipioRequest request) {
        Municipio municipio =
                new Municipio(request.id_pais(), request.id_estado(),
                        request.id_municipio(), request.municipio(), request.estatus());

        if (municipioService.saveMunicipio(municipio) != null) {
            municipio = municipioService.saveMunicipio(municipio);
            MunicipioResponse response =
                    new MunicipioResponse(municipio.getPais().getIdPais(),
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
    public ResponseEntity<List<MunicipioResponse>> getMunicipio(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        List<MunicipioResponse> response = municipioService.getMunicipios(page, size).stream()
                .map(municipio -> new MunicipioResponse(municipio.getPais().getIdPais(),
                        municipio.getEstado().getIdEstado(),
                        municipio.getIdMunicipio(),
                        municipio.getMunicipio(),
                        municipio.getEstatus())).toList();

        return ResponseEntity.ok(response);
    }

    // Obtener todos los municipio de un estado
    @GetMapping("/estado/{id}")
    public ResponseEntity<List<MunicipioResponse>> getMunicipioByIdEstado(@PathVariable Long id) {
        List<MunicipioResponse> response = municipioService.getMunicipiosByEstadoId(id).stream()
                .map(municipio -> new MunicipioResponse(municipio.getPais().getIdPais(),
                        municipio.getEstado().getIdEstado(),
                        municipio.getIdMunicipio(),
                        municipio.getMunicipio(),
                        municipio.getEstatus()))
                .toList();

        return ResponseEntity.ok(response);
    }


    // Obtener los municipio por ID
    @GetMapping("/{id}")
    public ResponseEntity<MunicipioResponse> getMunicipioById(@PathVariable Long id) {
        Optional<Municipio> municipio = municipioService.getMunicipioById(id);
        if (municipio.isPresent()) {
            MunicipioResponse response =
                    new MunicipioResponse(municipio.get().getPais().getIdPais(),
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
