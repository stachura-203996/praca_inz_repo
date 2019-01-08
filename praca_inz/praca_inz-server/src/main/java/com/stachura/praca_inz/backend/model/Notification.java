package com.stachura.praca_inz.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "NOTIFICATION")
@Getter
@Setter
public class Notification implements Serializable, Comparator<Notification>,Cloneable  {

    @Id
    @SequenceGenerator(name = "NotificationGen", sequenceName = "notification_id_seq",initialValue = 5,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "NotificationGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Column(name = "DESCRIPTION", nullable = false,  columnDefinition ="TEXT")
    private String description;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "READED")
    private boolean readed;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @Basic
    @NotNull
    @Column(name = "NOTIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar calendarTimestamp;


    @Override
    public int compare(Notification o1, Notification o2) {
        return o1.getCalendarTimestamp().compareTo(o2.getCalendarTimestamp());
    }




}
