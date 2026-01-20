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

    private String name;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "is_high_demand")
    private boolean isHighDemand;

    private LocalDate eventDate;
}
