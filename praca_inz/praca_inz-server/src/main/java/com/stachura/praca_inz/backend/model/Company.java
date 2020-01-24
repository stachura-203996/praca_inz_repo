package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@EnableAutoConfiguration
@Table(name = "COMPANY", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@EqualsAndHashCode(of = "id")
public class Company implements Serializable {

    @Id
    @SequenceGenerator(name = "CompanyGen", sequenceName = "company_id_seq",initialValue = 2,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CompanyGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @Column(name = "DESCRIPTION",columnDefinition ="TEXT")
    private String description;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAIN_OFFICE_ADRESS_ID")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Department> departments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Device> devices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<DeviceModel> deviceModels = new HashSet<>();

    public Company() {
    }

    public Company(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
