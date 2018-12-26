package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stachura.praca_inz.backend.model.security.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@EnableAutoConfiguration
@Table(name = "DEVICE_TYPE")
@Getter
@Setter
public class DeviceType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DEVICES_TYPES_PARAMETERS", joinColumns = @JoinColumn(name = "DEVICE_TYPE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PARAMETER_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Parameter> parameters;
}
