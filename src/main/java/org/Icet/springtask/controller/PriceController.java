package org.Icet.springtask.controller;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.PriceResult;
import org.Icet.springtask.entity.Event;
import org.Icet.springtask.entity.User;
import org.Icet.springtask.exception.ResourceNotFoundException;
import org.Icet.springtask.repository.EventRepository;
import org.Icet.springtask.repository.UserRepository;
import org.Icet.springtask.service.PriceCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceCalculatorService priceCalculatorService;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


        @PostMapping("/calculate")
        public ResponseEntity<?> calculatePrice(@RequestParam Long userId,
                                                 @RequestParam Long eventId) {


            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));


            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));


            PriceResult result = priceCalculatorService.calculatePrice(user, event);

            return ResponseEntity.ok(result);
        }
}

