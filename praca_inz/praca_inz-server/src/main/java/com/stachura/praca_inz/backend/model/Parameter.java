package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.security.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "PARAMETER")
@Getter
@Setter
public class Parameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<ParameterValue> parameterValues = new HashSet<>();

    public void setParametersValues(Set<ParameterValue> parameterValues) {
        this.parameterValues.clear();
        if (parameterValues != null) {
            this.parameterValues.addAll(parameterValues);
        }
    }
}
