package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Userdata> userdata = new HashSet<>();


    public void setUserdata(Set<Userdata> userdata) {
        this.userdata.clear();
        if (userdata != null) {
            this.userdata.addAll(userdata);
        }
    }
}
