package org.Icet.springtask.service;

import lombok.RequiredArgsConstructor;
import org.Icet.springtask.dto.PriceResult;
import org.Icet.springtask.entity.Event;
import org.Icet.springtask.entity.User;
import org.Icet.springtask.enums.UserTier;
import org.Icet.springtask.strategy.PlatinumPriceStrategy;
import org.Icet.springtask.strategy.PriceStrategy;
import org.Icet.springtask.strategy.RegularPriceStrategy;
import org.Icet.springtask.strategy.VipPriceStrategy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceCalculatorService {

    private final RegularPriceStrategy regularStrategy;
    private final VipPriceStrategy vipStrategy;
    private final PlatinumPriceStrategy platinumStrategy;

    public PriceResult calculatePrice(User user, Event event) {

        PriceStrategy strategy;

        if (user.getTier() == UserTier.REGULAR) {
            strategy = regularStrategy;
        } else if (user.getTier() == UserTier.VIP) {
            strategy = vipStrategy;
        } else {
            strategy = platinumStrategy;
        }

        return strategy.calculate(user, event);
    }
}