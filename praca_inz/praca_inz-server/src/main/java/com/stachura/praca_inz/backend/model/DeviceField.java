package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Table(name = "DEVICE_FIELD")
@Getter
@Setter
public class DeviceField {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DeviceType deviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Request request;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;


}
