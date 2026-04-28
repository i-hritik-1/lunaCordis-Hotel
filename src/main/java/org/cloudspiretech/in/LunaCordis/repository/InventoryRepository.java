package org.cloudspiretech.in.LunaCordis.repository;

import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
