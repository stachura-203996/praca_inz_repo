package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "NOTIFICATION")
@Getter
@Setter
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "READED")
    private boolean readed;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date notificationData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

}
