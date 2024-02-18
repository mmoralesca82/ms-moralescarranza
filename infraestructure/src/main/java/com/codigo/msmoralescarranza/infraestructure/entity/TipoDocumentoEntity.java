package com.codigo.msmoralescarranza.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.util.Times;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="tipo_documento")
public class TipoDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;
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
