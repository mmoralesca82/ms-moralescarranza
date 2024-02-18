package com.codigo.msmoralescarranza.domain.impl;

import com.codigo.msmoralescarranza.domain.aggregates.dto.PersonaDTO;
import com.codigo.msmoralescarranza.domain.aggregates.request.RequestPersona;
import com.codigo.msmoralescarranza.domain.aggregates.response.ResponseError;
import com.codigo.msmoralescarranza.domain.ports.in.PersonaServiceIn;
import com.codigo.msmoralescarranza.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    private ResponseError responseError = new ResponseError(404, " ");
    @Override
    public ResponseEntity<?> crearPersonaIn(RequestPersona requestPersona) {
        if (requestPersona.getTipoDoc().equals("DNI")) {
            try {
                Optional<PersonaDTO> existingPersona = personaServiceOut.obtenerPersonaOut(requestPersona.getNumDoc());
            }
            catch(Exception e){
                return new ResponseEntity<PersonaDTO>(
                        personaServiceOut.crearPersonaOut(requestPersona),
                        HttpStatus.CREATED);
            }
            responseError.setMessage("The register exists.");
            return new ResponseEntity<ResponseError>(responseError, HttpStatus.FORBIDDEN);
        }
        responseError.setMessage("Only DNI type document accepted in this moment.");
        return new ResponseEntity<ResponseError>(responseError, HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numDoc) {
        return personaServiceOut.obtenerPersonaOut(numDoc);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public ResponseEntity<?> actualizarIn(Long id, RequestPersona requestPersona) {
        if (requestPersona.getTipoDoc().equals("DNI")) {
            try {
                Optional<PersonaDTO> existingPersona = personaServiceOut.obtenerPersonaOut(requestPersona.getNumDoc());
                if(existingPersona.get().getIdPersona().equals(id)){
                    return new ResponseEntity<PersonaDTO>(
                            personaServiceOut.actualizarOut(id,requestPersona),
                            HttpStatus.GONE);
                }
            }
            catch(Exception e){
                return new ResponseEntity<PersonaDTO>(
                        personaServiceOut.actualizarOut(id,requestPersona),
                        HttpStatus.CREATED);
            }
            responseError.setMessage("The register exists.");
            return new ResponseEntity<ResponseError>(responseError, HttpStatus.FORBIDDEN);
        }
        responseError.setMessage("Only DNI type document accepted in this moment.");
        return new ResponseEntity<ResponseError>(responseError, HttpStatus.NOT_FOUND);

    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}
