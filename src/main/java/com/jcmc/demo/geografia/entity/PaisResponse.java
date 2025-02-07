package com.jcmc.demo.geografia.entity;

import java.io.Serializable;

public record PaisResponse(
        Long id_pais,
        String pais,
        Integer estatus
) implements Serializable {
}
