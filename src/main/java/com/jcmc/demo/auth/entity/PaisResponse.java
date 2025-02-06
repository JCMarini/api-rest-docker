package com.jcmc.demo.auth.entity;

import java.io.Serializable;

public record PaisResponse(
        Long id_pais,
        String pais,
        Integer estatus
) implements Serializable {
}
