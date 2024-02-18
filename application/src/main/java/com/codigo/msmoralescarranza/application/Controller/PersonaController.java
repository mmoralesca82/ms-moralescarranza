package com.codigo.msmoralescarranza.application.Controller;

import com.codigo.msmoralescarranza.domain.aggregates.dto.PersonaDTO;
import com.codigo.msmoralescarranza.domain.aggregates.request.RequestPersona;
import com.codigo.msmoralescarranza.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "API -PERSON",
                version = "2.0",
                description = "Mantenimiento de una Persona"
        )
)
@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;


    @Operation(summary = "Crea una Persona")
    @PostMapping
    public ResponseEntity<?> registroPersona(@RequestBody RequestPersona requestPersona) {
        return personaServiceIn.crearPersonaIn(requestPersona);
    }

    @Operation(summary = "Obtiene una persona por numero de documento")
    @GetMapping("/{numDoc}")
    public ResponseEntity<PersonaDTO> findByNumDoc(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(numDoc).get());
    }

    @Operation(summary = "Obtiene todas las personas cuyo estado es activo")
    @GetMapping()
    public ResponseEntity<List<PersonaDTO>> findAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());

    }

    @Operation(summary = "Actualiza una Persona")
    @PutMapping("/{id}")
    public ResponseEntity<?>actualizar(@PathVariable Long id,
                                       @RequestBody RequestPersona requestPersona){
        return personaServiceIn.actualizarIn(id,requestPersona);
    }

    @Operation(summary = "Elimina de manera lógica una Persona")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO>eliminar(@PathVariable Long id){
        return new ResponseEntity<>(personaServiceIn.deleteIn(id), HttpStatus.GONE);
    }
}
