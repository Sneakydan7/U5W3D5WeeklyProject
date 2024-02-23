package com.cagnoni.U5W3D5WeeklyProject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String eventTitle;
    private String description;
    private LocalDate date;
    private String location;
    private Integer maxCapacity;

    public Event(String eventTitle, String description, LocalDate date, String location, Integer maxCapacity) {
        this.eventTitle = eventTitle;
        this.description = description;
        this.date = date;
        this.location = location;
        this.maxCapacity = maxCapacity;
    }
}
