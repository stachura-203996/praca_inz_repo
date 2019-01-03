package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.security.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "DEVICE_MODEL")
@Getter
@Setter
public class DeviceModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MANUFACTURE")
    private String manufacture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DeviceType deviceType;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceModel", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<DeviceField> deviceFields = new HashSet<>();


    public void setDeviceTypes(Set<DeviceField> deviceFields) {
        this.deviceFields.clear();
        if (deviceFields != null) {
            this.deviceFields.addAll(deviceFields);
        }
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceModel", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Parameter> parameters = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceModel", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> devices = new HashSet<>();

}