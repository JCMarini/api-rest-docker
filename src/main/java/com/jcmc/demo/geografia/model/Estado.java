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
@Table(name = "estados")
public class Estado implements Serializable {

    public Estado(Integer idPais, Long idEstado, String estado, Integer estatus) {
        Pais pais = new Pais();
        pais.setIdPais(idPais);
        this.pais = pais;
        this.idEstado = idEstado;
        this.estado = estado;
        this.estatus = estatus;
        this.fecha = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Long idEstado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @Column(nullable = false, unique = true)
    private String estado;

    @Column(nullable = false)
    private Integer estatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
    private Date fecha;
}
