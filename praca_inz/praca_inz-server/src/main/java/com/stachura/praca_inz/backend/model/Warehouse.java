package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "WAREHOUSE")
@Getter
@Setter
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OFFICE_ID")
    private Office office;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> devices = new HashSet<>();


    public void setDevices(Set<Device> devices) {
        this.devices.clear();
        if (devices != null) {
            this.devices.addAll(devices);
        }
    }
}

