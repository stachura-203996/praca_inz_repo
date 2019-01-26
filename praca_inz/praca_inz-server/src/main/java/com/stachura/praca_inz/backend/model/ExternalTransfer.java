package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@EnableAutoConfiguration
@Table(name = "EXTERNAL_TRANSFER")
@Getter
@Setter
public class ExternalTransfer implements Serializable {

    @Id
    @SequenceGenerator(name = "DeliveryGen", sequenceName = "delivery_id_seq", initialValue = 3, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DeliveryGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "EXTERNAL_TRANSFER_NUMBER", nullable = false,unique = true)
    private String externalTransferNumber;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SERIAL_NUMBER", nullable = false,unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private DeviceModel deviceModel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @Basic
    @NotNull
    @Column(name = "EXTERNAL_TRANSFER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;

    @Basic
    @NotNull
    @Column(name = "CONFIRM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar confirmDate;

    @Column(name = "CONFIRMED", nullable = false)
    private boolean confirmed;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
}
