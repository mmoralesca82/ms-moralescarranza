package com.codigo.msmoralescarranza.domain.ports.out;

import com.codigo.msmoralescarranza.domain.aggregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getFromReniec(String numDocu);
}
