package org.Icet.springtask.controller;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.SeatResponse;
import org.Icet.springtask.entity.Seat;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.exception.SeatLockedException;
import org.Icet.springtask.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private static final Logger log = LoggerFactory.getLogger(SeatController.class);

    private final SeatService seatService;

    // Endpoint to hold a seat
    @PostMapping("/{seatId}/hold")
    public ResponseEntity<?> holdSeat(
            @PathVariable Long seatId,
            @RequestParam Long userId) {

        try {
            Seat heldSeat = seatService.holdSeat(seatId, userId);

            // Convert to DTO here (safe to serialize)
            return ResponseEntity.ok(SeatResponse.fromEntity(heldSeat));
        } catch (ResourceNotFoundException ex) {
            log.warn("Seat not found: seatId={}", seatId, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (SeatLockedException ex) {
            log.info("Seat locked when trying to hold: seatId={} userId={}", seatId, userId, ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            // log full stacktrace so we can see the nested cause that produced "Could not commit JPA transaction"
            log.error("Unexpected error while holding seat seatId={} userId={}", seatId, userId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }
}