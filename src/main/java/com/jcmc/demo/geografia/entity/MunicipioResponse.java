package com.jcmc.demo.geografia.entity;

import java.io.Serializable;

public record MunicipioResponse(
        Long id_pais,
        Long id_estado,
        Long id_municipio,
        String municipio,
        Integer estatus
) implements Serializable {
}
