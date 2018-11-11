package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEVICE")
@Getter
@Setter
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse warehouse;
}
