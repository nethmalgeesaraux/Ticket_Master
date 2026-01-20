package org.Icet.springtask.service;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.BookingResponse;
import org.Icet.springtask.dto.PriceResult;
import org.Icet.springtask.entity.Booking;
import org.Icet.springtask.entity.Event;
import org.Icet.springtask.entity.Seat;
import org.Icet.springtask.entity.User;
import org.Icet.springtask.enums.BookingStatus;
import org.Icet.springtask.enums.SeatStatus;
import org.Icet.springtask.exception.BookingFailedException;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.repository.BookingRepository;
import org.Icet.springtask.repository.SeatRepository;
import org.Icet.springtask.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;

    @Transactional
    @AuditFailure
    public BookingResponse bookSeat(Long userId, Long seatId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seat not found"));

        if (seat.getStatus() != SeatStatus.HELD ||
                !userId.equals(seat.getHeldByUserId())) {
            throw new BookingFailedException("Seat is not held by this user");
        }

        Event event = seat.getEvent();

        PriceResult priceResult =
                priceCalculatorService.calculatePrice(user, event);

        // Simulate payment success
        seat.setStatus(SeatStatus.SOLD);
        seatRepository.save(seat);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(priceResult.getFinalPrice());
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

        return new BookingResponse(
                booking.getId(),
                booking.getStatus().name(),
                booking.getAmountPaid(),
                priceResult.isPriorityAccess(),
                "Booking confirmed successfully"
        );
    }
}