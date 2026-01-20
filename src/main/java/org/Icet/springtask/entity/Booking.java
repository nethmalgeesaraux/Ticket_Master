package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Icet.springtask.enums.BookingStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "amount_paid")
    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
