package org.Icet.springtask.strategy;


import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.PriceResult;
import org.Icet.springtask.entity.Event;
import org.Icet.springtask.entity.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegularPriceStrategy implements PriceStrategy {

    @Override
    public PriceResult calculate(User user, Event event) {
        double basePrice = event.getBasePrice();
        return new PriceResult(basePrice, "Regular price applied");
    }
}
