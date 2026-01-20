package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="seat_number")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private org.Icet.springtask.enums.SeatStatus status;

    @Column(name="held_by_user_id")
    private Long heldByUserId;

    @Column(name="hold_expiry")
    private LocalDateTime holdExpiry;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;


    @Version
    @Column(name = "version", nullable = false)
    private long version;
}