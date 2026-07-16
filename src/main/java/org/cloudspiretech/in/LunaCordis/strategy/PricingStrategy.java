package org.cloudspiretech.in.LunaCordis.strategy;

import org.cloudspiretech.in.LunaCordis.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    public BigDecimal calculatePrice(Inventory inventory);
}
