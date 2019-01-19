package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "DEVICE")
@Getter
@Setter
public class Device implements Serializable {

    @Id
    @SequenceGenerator(name = "DeviceGen", sequenceName = "device_id_seq",initialValue = 10,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DeviceGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "SERIAL_NUMBER",nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private DeviceModel deviceModel;

    @Basic
    @NotNull
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdate;

    @Basic
    @NotNull
    @Column(name = "CREATE_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Warehouse warehouse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Transfer> transfers = new HashSet<>();

    public Device() {
    }

    public Device(Long id, long version) {
        this.id=id;
        this.version = version;
    }

}
