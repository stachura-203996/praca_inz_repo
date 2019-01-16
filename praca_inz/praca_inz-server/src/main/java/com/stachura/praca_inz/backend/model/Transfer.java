package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stachura.praca_inz.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "TRANSFER")
@Getter
@Setter
public class Transfer implements Serializable {

    @Id
    @SequenceGenerator(name = "TransferGen", sequenceName = "transfer_id_seq",initialValue = 2,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TransferGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @NotNull
    @Column(name = "TRANSFER_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Device device;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    public Transfer() {
    }

    public Transfer(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
