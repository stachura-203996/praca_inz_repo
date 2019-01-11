package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification,Long> {
}
