package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.NotificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl extends AbstractRepository<Notification> implements NotificationRepository {

    public NotificationRepositoryImpl() {super(Notification.class);}
}
