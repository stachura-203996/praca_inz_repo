package com.stachura.praca_inz.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EnableAutoConfiguration
@Table(name = "ADDRESS")
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "AddressGen", sequenceName = "address_id_seq",initialValue = 11,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AddressGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET",nullable = false)
    private String street;

    @Column(name = "BUILDING_NUMBER",nullable = false)
    private String buildingNumber;

    @Column(name = "FLAT_NUMBER")
    private String flatNumber;


    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    public Address() {
    }

    public Address(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
