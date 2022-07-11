package com.gradinar.mynotes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "notes_id_seq",
            allocationSize = 1
    )
    private long id;
    @NotEmpty
    private String caption;
    @NotEmpty
    private String content;

    @JsonIgnore
    @ManyToOne
    private User user;

}
