package org.Icet.springtask.service;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.entity.Seat;
import org.Icet.springtask.enums.SeatStatus;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.exception.SeatLockedException;
import org.Icet.springtask.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public Seat holdSeat(Long seatId, Long userId) {
        Seat seat = seatRepository.findByIdForUpdate(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();

        if (seat.getStatus() == SeatStatus.AVAILABLE ||
                seat.getHoldExpiry() == null ||
                seat.getHoldExpiry().isBefore(now)) {

            seat.setStatus(SeatStatus.HELD);
            seat.setHeldByUserId(userId);
            seat.setHoldExpiry(now.plusMinutes(10));
            return seatRepository.save(seat);
        }

        throw new SeatLockedException("Seat is already held");
    }


}
