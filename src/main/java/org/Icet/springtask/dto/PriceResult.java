package org.Icet.springtask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceResult {

    private double finalPrice;
    private String message;

    public boolean isPriorityAccess() {
        return finalPrice > 100.0;
    }
}
