package org.Icet.springtask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequest {

    private Long userId;
    private Long seatId;
}
