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

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id", nullable = false)
        private Event event;

        @Column(name = "seat_number", nullable = false)
        private String seatNumber;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private SeatStatus status;

        @Column(name = "held_by_user_id")
        private Long heldByUserId;

        @Column(name = "hold_expiry")
        private LocalDateTime holdExpiry;

        @Version
        private Long version;
}
