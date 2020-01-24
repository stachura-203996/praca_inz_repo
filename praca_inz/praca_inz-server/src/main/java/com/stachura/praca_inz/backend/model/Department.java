package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "DEPARTMENT")
public class Department implements Serializable {

    @Id
    @SequenceGenerator(name = "DepartmentGen", sequenceName = "department_id_seq",initialValue = 6,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DepartmentGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @Column(name = "DESCRIPTION" , columnDefinition ="TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private Company company;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Office> offices = new HashSet<>();
}
