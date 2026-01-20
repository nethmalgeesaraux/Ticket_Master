package org.Icet.springtask.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private String error;
    private String message;
    private LocalDateTime timestamp;
}
