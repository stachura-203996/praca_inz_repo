package com.stachura.praca_inz.backend.model.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@EnableAutoConfiguration
@EqualsAndHashCode(of = "id")
@Table(name = "AUTHORITY", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "NAME",nullable = false,unique = true)
    private String name;

    @Column(name= "ACTIVE",nullable = false)
    private Boolean active;

    @Override
    public String getAuthority() {
        return getName();
    }
}
