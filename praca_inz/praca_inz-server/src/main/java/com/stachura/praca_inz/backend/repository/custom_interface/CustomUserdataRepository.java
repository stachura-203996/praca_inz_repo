package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Userdata;

public interface CustomUserdataRepository {
    void detach(Userdata entity);
}
