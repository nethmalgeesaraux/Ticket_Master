package org.Icet.springtask.dto;

import lombok.*;
import org.Icet.springtask.entity.Seat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {

    private Long id;
    private String seatNumber;
    private String status;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
    private EventResponse event;

    public static SeatResponse fromEntity(Seat seat) {
        return new SeatResponse(
                seat.getId(),
                seat.getSeatNumber(),
                seat.getStatus().name(),
                seat.getHeldByUserId(),
                seat.getHoldExpiry(),
                EventResponse.fromEntity(seat.getEvent())
        );
    }
}
