package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    Void deleteHotelById(Long id);
    void activateHotel(Long id);

}
