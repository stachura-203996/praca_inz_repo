package com.stachura.praca_inz.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@EnableAutoConfiguration
@Table(name = "SYSTEM_MESSAGE")
@Getter
@Setter
public class SystemMessage implements Serializable, Comparator<SystemMessage> {

    @Id
    @SequenceGenerator(name = "SystemMessageGen", sequenceName = "system_message_id_seq",initialValue = 2,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SystemMessageGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @Basic
    @NotNull
    @Column(name = "MESSAGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar calendarTimestamp;

    public SystemMessage() {
    }

    public SystemMessage(Long id, long version) {
        this.id=id;
        this.version = version;
    }

    @Override
    public int compare(SystemMessage o1, SystemMessage o2) {
        return o1.getCalendarTimestamp().compareTo(o2.getCalendarTimestamp());
    }
}
