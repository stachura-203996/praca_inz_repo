package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "OFFICE")
@Getter
@Setter
public class Office implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Department department;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER_ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public void setUsers(Set<User> users) {
        this.users.clear();
        if (users != null) {
            this.users.addAll(users);
        }
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse;
}
