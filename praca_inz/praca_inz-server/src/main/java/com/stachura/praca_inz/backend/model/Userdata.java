package com.stachura.praca_inz.backend.model;

import com.stachura.praca_inz.backend.validation.ValidEmail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "USERDATA")
public class Userdata implements Serializable {

    @Id
    @SequenceGenerator(name = "UserdataGen", sequenceName = "userdata_id_seq",initialValue = 6,allocationSize = 1)
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

    @ValidEmail
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
    @Column(name = "JOIN_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateOfJoin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
}
