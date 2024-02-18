package com.codigo.msmoralescarranza.infraestructure.repository;

import com.codigo.msmoralescarranza.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {

    TipoPersonaEntity findByCodTipo(@Param("codTipo") String codTipo);
}
