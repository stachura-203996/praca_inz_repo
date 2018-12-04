package com.stachura.praca_inz.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Table(name = "TRANSITION")
@Getter
@Setter
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

}
