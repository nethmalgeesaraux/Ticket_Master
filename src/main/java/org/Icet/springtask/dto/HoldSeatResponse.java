package org.Icet.springtask.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HoldSeatResponse {

    private Long seatId;
    private String seatStatus;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
    private String message;
}
