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
    @SequenceGenerator(name = "ReportGen", sequenceName = "report_id_seq",initialValue = 3,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ReportGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private User reciever;

    @Column(name = "DESCRIPTION", columnDefinition ="TEXT", nullable = false)
    private String description;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @Column(name = "DISABLE_SENDER", nullable = false)
    private boolean disableSender;

    @Column(name = "DISABLE_RECIEVER", nullable = false)
    private boolean disableReciever;

    @Basic
    @NotNull
    @Column(name = "REPORT_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;

    public Report() {
    }

    public Report(Long id, long version) {
        this.id=id;
        this.version = version;
    }

    @Override
    public int compare(Report o1, Report o2) {
        return o1.getCreateDate().compareTo(o2.getCreateDate());
    }
}
