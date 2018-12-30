package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Notification;

import java.util.List;

public interface NotificationRepository {
    Notification find(Long id);

    List<Notification> findAll();

    void create(Notification office)throws EntityException;

    Notification update(Notification office)throws EntityException;

    void remove(Long id);

    void remove(Notification office);
}
