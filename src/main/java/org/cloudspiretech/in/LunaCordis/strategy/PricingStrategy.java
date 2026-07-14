package org.cloudspiretech.in.LunaCordis.strategy;

import org.cloudspiretech.in.LunaCordis.entity.Inventory;

public interface PricingStrategy {
    public double calculatePrice(Inventory inventory);
}
