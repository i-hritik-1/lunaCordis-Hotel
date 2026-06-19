package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelSearchRequestDto;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteFutureInventory(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequestDto hotelSearchRequestDto);
}
