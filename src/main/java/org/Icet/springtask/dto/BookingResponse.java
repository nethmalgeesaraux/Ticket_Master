package org.Icet.springtask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponse {

    private Long bookingId;
    private String status;
    private double amountPaid;
    private boolean priorityAccess;
    private String message;
}
