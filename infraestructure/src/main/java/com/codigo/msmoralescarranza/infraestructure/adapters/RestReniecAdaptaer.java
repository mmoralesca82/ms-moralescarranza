package com.codigo.msmoralescarranza.infraestructure.adapters;

import com.codigo.msmoralescarranza.domain.aggregates.response.ResponseReniec;
import com.codigo.msmoralescarranza.domain.ports.out.RestReniecOut;
import com.codigo.msmoralescarranza.infraestructure.rest.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdaptaer implements RestReniecOut {

    private final ClienteReniec reniec;

    @Value("${token.api}")
    private String tokenApi;
    @Override
    public ResponseReniec getFromReniec(String numDoc) {
        String authotization = "Bearer "+ tokenApi;
        return  reniec.getFromReniec(numDoc, authotization);
    }
}
