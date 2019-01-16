package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stachura.praca_inz.backend.model.enums.RequestType;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name="REQUEST")
@EnableAutoConfiguration
@Getter
@Setter
public class Request {

    @Id
    @SequenceGenerator(name = "RequestGen", sequenceName = "request_id_seq",initialValue = 6,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "RequestGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private DeviceModel deviceModel;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;

    @Column(name = "REQUEST_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "DESCRIPTION" , columnDefinition ="TEXT",nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @Basic
    @NotNull
    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "REQUESTS_DEVICES", joinColumns = @JoinColumn(name = "REQUEST_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Device> devices;

    public Request() {
    }

    public Request(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
