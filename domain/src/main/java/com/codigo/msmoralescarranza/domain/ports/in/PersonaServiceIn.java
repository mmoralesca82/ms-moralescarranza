package com.codigo.msmoralescarranza.domain.ports.in;

import com.codigo.msmoralescarranza.domain.aggregates.dto.PersonaDTO;
import com.codigo.msmoralescarranza.domain.aggregates.request.RequestPersona;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    ResponseEntity<?> crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(String NumDocu);
    List<PersonaDTO> obtenerTodosIn();
    ResponseEntity<?> actualizarIn(Long id, RequestPersona requestPersona);
    PersonaDTO deleteIn(Long id);

}
