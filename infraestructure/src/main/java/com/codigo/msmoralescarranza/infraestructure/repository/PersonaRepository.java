package com.codigo.msmoralescarranza.infraestructure.repository;

import com.codigo.msmoralescarranza.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<List<PersonaEntity>> findByEstado(Integer estado);
    Optional<PersonaEntity> findByNumDocu(String numDoc);
}
