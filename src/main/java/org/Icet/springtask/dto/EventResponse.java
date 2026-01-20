package org.Icet.springtask.dto;

import lombok.*;
import org.Icet.springtask.entity.Event;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private Long id;
    private String name;
    private double basePrice;
    private boolean isHighDemand;
    private LocalDate eventDate;

    public static EventResponse fromEntity(Event event) {
        return new EventResponse(
                event.getId(),
                event.getName(),
                event.getBasePrice(),
                event.isHighDemand(),
                event.getEventDate()
        );
    }
}
