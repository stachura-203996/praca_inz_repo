package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "DEVICE")
@Getter
@Setter
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_TYPE_ID")
    private DeviceType deviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse warehouse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Parameter> parameters = new HashSet<>();


    public void setUsers(Set<Parameter> parameters) {
        this.parameters.clear();
        if (parameters != null) {
            this.parameters.addAll(parameters);
        }
    }
}
