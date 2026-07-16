package org.cloudspiretech.in.LunaCordis.strategy;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SurgePriceStrategy implements PricingStrategy {
    private final PricingStrategy wrapped;
    public BigDecimal calculatePrice(Inventory inventory) {
        return wrapped.calculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
