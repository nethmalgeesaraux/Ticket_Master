package org.Icet.springtask.strategy;

import org.Icet.springtask.dto.PriceResult;
import org.Icet.springtask.entity.Event;
import org.Icet.springtask.entity.User;

public interface PriceStrategy {

    PriceResult calculate(User user, Event event);
}
