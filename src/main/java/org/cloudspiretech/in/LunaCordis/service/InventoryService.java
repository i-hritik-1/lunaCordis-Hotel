package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteFutureInventory(Room room);
}
