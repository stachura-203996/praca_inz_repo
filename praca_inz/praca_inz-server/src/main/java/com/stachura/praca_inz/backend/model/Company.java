package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "COMPANY", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_OFFICE_ADRESS_ID")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Department> departments = new HashSet<>();



    public void setDepartments(Set<Department> departments) {
        this.departments.clear();
        if (departments != null) {
            this.departments.addAll(departments);
        }
    }


}
