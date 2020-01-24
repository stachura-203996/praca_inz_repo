package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "OFFICE")
public class Office implements Serializable {

    @Id
    @SequenceGenerator(name = "OfficeGen", sequenceName = "office_id_seq",initialValue = 5,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OfficeGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Department department;

    @Column(name = "DESCRIPTION",columnDefinition ="TEXT")
    private String description;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Warehouse> warehouses = new HashSet<>();
}
