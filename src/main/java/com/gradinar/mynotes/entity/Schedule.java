package com.gradinar.mynotes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@Data
public class Schedule {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator-event"
    )
    @SequenceGenerator(
            name = "sequence-generator-event",
            sequenceName = "events_id_seq",
            allocationSize = 1
    )
    private Long id;
    private Date event_date;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Event> events;

    @ManyToOne
    @JsonIgnore
    private User user;
}
