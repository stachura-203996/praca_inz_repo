package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "USERDATA")
@Getter
@Setter
public class Userdata implements Serializable {

    @Id
    @SequenceGenerator(name = "UserdataGen", sequenceName = "userdata_id_seq",initialValue = 4,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserdataGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "EMAIL", nullable = false,unique = true)
    private String email;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "WORKPLACE")
    private String workplace;

    @Column(name="LANGUAGE")
    private String language;

    @Basic
    @NotNull
    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateOfJoin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public Userdata() {
    }

    public Userdata(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
