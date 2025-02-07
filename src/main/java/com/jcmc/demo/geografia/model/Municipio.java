package com.jcmc.demo.geografia.model;

import com.jcmc.demo.auth.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "municipios")
public class Municipio implements Serializable {

    public Municipio(Long idPais, Long idEstado, Long idMunicipio, String municipio, Integer estatus){
        Pais pais = new Pais();
        pais.setIdPais(idPais);

        Estado estado = new Estado();
        estado.setIdEstado(idEstado);

        User user = new User();
        user.setIdUsuario(1);

        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.estatus = estatus;
        this.user = user;
        this.fecha = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio", nullable = false)
    private Long idMunicipio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false)
    private Integer estatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
    private Date fecha;
}
