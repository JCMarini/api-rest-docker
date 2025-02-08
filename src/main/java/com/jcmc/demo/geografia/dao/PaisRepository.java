package com.jcmc.demo.geografia.dao;

import com.jcmc.demo.core.util.SessionUtil;
import com.jcmc.demo.geografia.model.Pais;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    default Pais savePais(Pais pais) {
        pais.setUser(SessionUtil.getUser());
        return this.save(pais);
    }

    @Modifying
    @Transactional
    @Query(value = "UPDATE paises p SET p.pais=:pais, p.estatus=:estatus, " +
            "p.id_user=:idUsuario WHERE p.id_pais=:idPais", nativeQuery = true)
    Integer updatePais(Integer idPais, String pais, Integer estatus, Integer idUsuario);

}
