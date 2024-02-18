package com.codigo.msmoralescarranza.infraestructure.rest;

import com.codigo.msmoralescarranza.domain.aggregates.response.ResponseReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="reniec-client", url="https://api.apis.net.pe/v2/reniec/")
public interface ClienteReniec {

    @GetMapping("/dni")
    ResponseReniec getFromReniec(@RequestParam("numero") String numero,
                                 @RequestHeader("Authorization") String authorizationHeader);
}
