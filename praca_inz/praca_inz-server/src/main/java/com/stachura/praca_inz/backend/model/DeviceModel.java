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
    @SequenceGenerator(name = "DeviceModelGen", sequenceName = "device_model_id_seq",initialValue = 3,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DeviceModelGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @Column(name = "MANUFACTURE" ,nullable = false)
    private String manufacture;

    @Column(name = "COST", nullable = false)
    private long cost;

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
    private Set<Parameter> parameters = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceModel", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> devices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceModel", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> deliveries = new HashSet<>();

}
