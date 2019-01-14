package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "DELIVERY")
@Getter
@Setter
public class Delivery implements Serializable {

    @Id
    @SequenceGenerator(name = "DeliveryGen", sequenceName = "delivery_id_seq",initialValue = 2,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DeliveryGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "DELIVERY_NUMBER", nullable = false)
    private String deliveryNumber;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONFIRMED", nullable = false)
    private boolean confirmed;

    @Column(name = "SERIAL_NUMBER", nullable = false)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DeviceModel deviceModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @Basic
    @NotNull
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;


    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
}
