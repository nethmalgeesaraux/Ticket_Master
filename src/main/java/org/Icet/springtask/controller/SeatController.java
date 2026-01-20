package org.Icet.springtask.controller;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.entity.Seat;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.exception.SeatLockedException;
import org.Icet.springtask.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/{seatId}/hold")
    public ResponseEntity<?> holdSeat(@PathVariable Long seatId,
                                      @RequestParam Long userId) {

        try {
            Seat heldSeat = seatService.holdSeat(seatId, userId);
            return ResponseEntity.ok(heldSeat);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (SeatLockedException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }
}
