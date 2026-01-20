package org.Icet.springtask.service;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.entity.Seat;
import org.Icet.springtask.enums.SeatStatus;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.exception.SeatLockedException;
import org.Icet.springtask.repository.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SeatService {

    private static final Logger log = LoggerFactory.getLogger(SeatService.class);

    private final SeatRepository seatRepository;

    @Transactional
    public Seat holdSeat(Long seatId, Long userId) {
        try {
            Seat seat = seatRepository.findByIdForUpdate(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

            if (seat.getStatus() == SeatStatus.HELD &&
                    seat.getHoldExpiry() != null &&
                    seat.getHoldExpiry().isAfter(LocalDateTime.now()) &&
                    !Objects.equals(seat.getHeldByUserId(), userId)) {
                throw new SeatLockedException("Seat is currently held by another user");
            }

            seat.setStatus(SeatStatus.HELD);
            seat.setHeldByUserId(userId);
            seat.setHoldExpiry(LocalDateTime.now().plusMinutes(15));

            Seat saved = seatRepository.save(seat);

            // Ensure the event association is initialized while transaction/session is still open.
            // This avoids LazyInitializationException when the controller/JSON serializer accesses event.
            if (saved.getEvent() != null) {
                // touch a property to force initialization
                saved.getEvent().getId();
            }

            return saved;
        } catch (PessimisticLockingFailureException ex) {
            log.warn("Could not acquire DB lock for seatId={}", seatId, ex);
            throw new SeatLockedException("Seat is currently locked, please retry");
        }
    }
}