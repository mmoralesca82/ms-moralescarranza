package com.codigo.msmoralescarranza.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="tipo_persona")
public class TipoPersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPersona;
    @Column(nullable = false, length = 45)
    private String codTipo;
    @Column(nullable = false, length = 45)
    private String descTipo;
    @Column(nullable = false)
    private Integer estado;
    @Column(nullable = false, length = 45)
    private  String usuaCrea;
    private Timestamp dateCreate;
    @Column(nullable = false, length = 45)
    private  String usuaModif;
    private Timestamp dateModif;
    @Column(nullable = false, length = 45)
    private  String usuaDelet;
    private Timestamp dateDelet;
}
