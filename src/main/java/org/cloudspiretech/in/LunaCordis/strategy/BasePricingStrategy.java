package org.cloudspiretech.in.LunaCordis.strategy;

import org.cloudspiretech.in.LunaCordis.entity.Inventory;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy{
    public BigDecimal calculatePrice(Inventory inventory){
        return inventory.getRoom().getBasePrice();
    }
}
