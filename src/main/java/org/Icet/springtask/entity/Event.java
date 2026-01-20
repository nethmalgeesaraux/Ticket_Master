package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @Column(name = "is_high_demand", nullable = false)
    private boolean isHighDemand;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;
}
