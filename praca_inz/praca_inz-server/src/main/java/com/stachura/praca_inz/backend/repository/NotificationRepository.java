package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
