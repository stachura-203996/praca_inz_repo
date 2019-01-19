package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
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
    @SequenceGenerator(name = "WarehouseGen", sequenceName = "warehouse_id_seq",initialValue = 10,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "WarehouseGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Office office;

    @Column(name = "WAREHOUSE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private WarehouseType warehouseType;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> devices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Request> senderRequests = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recieverWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Request> receiverRequests = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recieverWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<ExternalTransfer> receiverDeliveries = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<ExternalTransfer> senderDeliveries = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recieverWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<ExternalTransfer> receiverShipments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<ExternalTransfer> senderShipments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Transfer> senderTransfers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recieverWarehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Transfer> receiverTransfers = new HashSet<>();

    public Warehouse() {
    }

    public Warehouse(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}

