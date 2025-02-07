package com.jcmc.demo.geografia.dao;

import com.jcmc.demo.geografia.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRespository extends JpaRepository<Estado, Long> {

    @Query(value = """
            SELECT e.id_estado, e.estado, e.estatus, e.fecha, e.id_pais, e.id_user \s
            FROM estados e \s
            inner join paises p \s
            on e.id_pais = p.id_pais \s 
            WHERE e.id_pais = :id""", nativeQuery=true)
    List<Estado> getEstadosByPais(@Param("id") Long id);

}
