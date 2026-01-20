package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Icet.springtask.enums.SeatStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Column(name = "held_by_user_id")
    private Long heldByUserId;

    @Column(name = "hold_expiry")
    private LocalDateTime holdExpiry;

    @Version
    private Long version;
}
