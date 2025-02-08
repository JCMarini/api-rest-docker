package com.jcmc.demo.geografia.entity;

import java.io.Serializable;

public record EstadoEntity(
        Integer id_pais,
        Long id_estado,
        String estado,
        Integer estatus) implements Serializable {
}
