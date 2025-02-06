package com.jcmc.demo.auth.entity;

public record EstadoResponse(
        Long id_pais,
        Long id_estado,
        String estado,
        Integer estatus) {
}
