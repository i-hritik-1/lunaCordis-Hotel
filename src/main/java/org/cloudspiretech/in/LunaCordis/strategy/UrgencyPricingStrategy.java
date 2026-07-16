package org.cloudspiretech.in.LunaCordis.strategy;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {

        BigDecimal price = wrapped.calculatePrice(inventory);

        LocalDateTime now = LocalDateTime.now();


        return null;
    }
}
