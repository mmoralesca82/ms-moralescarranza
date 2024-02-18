package com.codigo.msmoralescarranza.infraestructure.adapters;

import com.codigo.msmoralescarranza.domain.aggregates.constants.Constants;
import com.codigo.msmoralescarranza.domain.aggregates.dto.PersonaDTO;
import com.codigo.msmoralescarranza.domain.aggregates.request.RequestPersona;


import com.codigo.msmoralescarranza.domain.aggregates.response.ResponseReniec;
import com.codigo.msmoralescarranza.domain.ports.out.PersonaServiceOut;
import com.codigo.msmoralescarranza.infraestructure.entity.PersonaEntity;
import com.codigo.msmoralescarranza.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msmoralescarranza.infraestructure.entity.TipoPersonaEntity;
import com.codigo.msmoralescarranza.infraestructure.mapper.PersonaMapper;
import com.codigo.msmoralescarranza.infraestructure.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;
    private final RestReniecAdaptaer reniec;
    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
            ResponseReniec datosReniec = reniec.getFromReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntity(datosReniec,requestPersona));
            return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(String numDocu) {
        return Optional.ofNullable(personaMapper.mapToDto(personaRepository.findByNumDocu(numDocu).get()));
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findByEstado(1).orElse(Collections.emptyList());
        if(entities.size()>0){
            for(PersonaEntity persona : entities){
                PersonaDTO personaDTO = personaMapper.mapToDto(persona);
                personaDTOList.add(personaDTO);
            }
        }
        return personaDTOList;

    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
          if(personaRepository.existsById(id)){
              Optional<PersonaEntity> entity = personaRepository.findById(id);
              ResponseReniec responseReniec = reniec.getFromReniec(requestPersona.getNumDoc());
              personaRepository.save(getEntityUpdate(responseReniec, entity.get(),requestPersona.getTipoPer()));
              return personaMapper.mapToDto(getEntityUpdate(responseReniec, entity.get(),requestPersona.getTipoPer()));
          }
          return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDto(entity.get());
        }
        return null;
    }


    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipopersona(tipoPersona);
        return entity;
    }
    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar, String codTipoPersona){
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(codTipoPersona);
        personaActualizar.setNumDocu(reniec.getNumeroDocumento());
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        personaActualizar.setTipopersona(tipoPersona);
        return personaActualizar;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
