package org.Icet.springtask.controller;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.BookingResponse;
import org.Icet.springtask.exception.BookingFailedException;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/book")
    public ResponseEntity<?> bookSeat(@RequestParam Long userId,
                                      @RequestParam Long seatId) {

        try {
            BookingResponse response = bookingService.bookSeat(userId, seatId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (BookingFailedException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }
}
