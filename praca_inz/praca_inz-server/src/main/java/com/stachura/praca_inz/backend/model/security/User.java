package com.stachura.praca_inz.backend.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "USER_")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails, Serializable {

    @Id
    @SequenceGenerator(name = "UserGen", sequenceName = "user_id_seq", initialValue = 6, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "USER_NAME",nullable = false,unique = true)
    private String username;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "ACCOUNT_EXPIRED",nullable = false)
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED",nullable = false)
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED",nullable = false)
    private boolean credentialsExpired;

    @Column(name = "ENABLED",nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonBackReference
    private Office office;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "USERDATA_ID")
    private Userdata userdata;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Warehouse> warehouses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Request> requests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Transfer> transfers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Confirmation> reportsSender = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reciever", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Confirmation> reportsReciever = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Notification> notifications = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<UserRole> userRoles;

    @Override
    public Collection<Authority> getAuthorities() {
        return this.getUserRoles().stream().flatMap(x -> x.getAuthorities().stream()).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }
}
