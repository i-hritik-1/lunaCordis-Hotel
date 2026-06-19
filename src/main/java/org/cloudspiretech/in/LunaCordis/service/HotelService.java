package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelInfoDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long id);
    List<HotelDto> getAllHotels();

    HotelInfoDto getHotelInfoById(Long hotelId);
}
