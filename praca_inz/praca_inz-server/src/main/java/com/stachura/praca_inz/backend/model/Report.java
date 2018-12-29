package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@EnableAutoConfiguration
@Table(name = "REPORT")
@Getter
@Setter
public class Report implements Serializable, Comparator<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User reciever;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @Basic
    @NotNull
    @Column(name = "REPORT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar calendarTimestamp;


    @Override
    public int compare(Report o1, Report o2) {
        return o1.getCalendarTimestamp().compareTo(o2.getCalendarTimestamp());
    }
}
