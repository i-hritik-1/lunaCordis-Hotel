package org.cloudspiretech.in.LunaCordis.strategy;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {

        BigDecimal price = wrapped.calculatePrice(inventory);
        Boolean isTodayHoliday = true; // TODO : Call api to check the holiday or you can use the hard coded value for holiday

        if (isTodayHoliday)
        {
            price = price.multiply(BigDecimal.valueOf(1.8));
        }

        return price;
    }
}
