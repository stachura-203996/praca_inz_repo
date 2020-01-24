package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "DEVICE_TYPE")
public class DeviceType {

    @Id
    @SequenceGenerator(name = "DeviceTypeGen", sequenceName = "device_type_id_seq",initialValue = 7,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DeviceTypeGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceType", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<DeviceModel> deviceModels = new HashSet<>();
}
