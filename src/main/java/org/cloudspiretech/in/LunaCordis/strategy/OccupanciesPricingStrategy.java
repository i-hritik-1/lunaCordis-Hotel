package org.cloudspiretech.in.LunaCordis.strategy;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OccupanciesPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {

        BigDecimal price = inventory.getRoom().getBasePrice();

        double occupancyRate = (double) inventory.getBookedCount()/inventory.getTotalCount();

        if (occupancyRate > 0.8)
        {
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }
}
