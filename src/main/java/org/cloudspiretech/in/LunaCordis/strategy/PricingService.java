package org.cloudspiretech.in.LunaCordis.strategy;

import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

    public BigDecimal calculateDynamicPricing(Inventory inventory)
    {
        PricingStrategy pricingStrategy = new BasePricingStrategy();
        pricingStrategy = new HolidayPricingStrategy(pricingStrategy);
        pricingStrategy = new OccupanciesPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy = new SurgePriceStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }
}
