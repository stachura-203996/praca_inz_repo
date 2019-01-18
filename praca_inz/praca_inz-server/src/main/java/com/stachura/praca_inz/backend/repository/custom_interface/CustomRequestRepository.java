package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Request;

public interface CustomRequestRepository {
    void detach(Request entity);
}
