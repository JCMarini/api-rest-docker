package com.jcmc.demo.geografia.dao;

import com.jcmc.demo.core.util.SessionUtil;
import com.jcmc.demo.geografia.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query(value = """
            SELECT m.id_pais, m.id_estado, m.id_municipio, m.municipio, m.estatus, m.fecha,  m.id_user \s
            FROM municipios m \s
            inner join estados e \s
            on m.id_estado = e.id_estado \s 
            WHERE m.id_estado = :id  \s
            order by id_municipio asc \s
            """, nativeQuery=true)
    List<Municipio> getMunicipiosByEstadoId(Long id);


     default Municipio saveMunicipio(Municipio municipio){
        municipio.setUser(SessionUtil.getUser());
        return save(municipio);
    }

}
