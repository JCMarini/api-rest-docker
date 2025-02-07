package com.jcmc.demo.geografia.entity;

import java.io.Serializable;

public record EstadoResponse(
        Long id_pais,
        Long id_estado,
        String estado,
        Integer estatus) implements Serializable {
}
