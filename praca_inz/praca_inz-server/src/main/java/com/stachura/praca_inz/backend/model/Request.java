package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.DeviceField;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.RequestType;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="REQUEST")
@EnableAutoConfiguration
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "REQUEST_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACCEPTED_TO_SEND", nullable = false)
    private boolean acceptedToSend;

    @Column(name = "ACCEPTED_TO_RECIEVE", nullable = false)
    private boolean acceptedToRecive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date utilTimestamp;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<DeviceField> deviceFields = new HashSet<>();

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
}
