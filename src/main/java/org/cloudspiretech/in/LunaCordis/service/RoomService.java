package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.dto.RoomDto;

import java.util.List;
public interface RoomService {

    RoomDto createNewRoom(RoomDto roomDto);
    RoomDto updateRoom(RoomDto roomDto);
    RoomDto getRoomById(Long id);
    void deleteRoomById(Long id);
    List<RoomDto> getAllRoomsOfHotel(Long hotelId);

}
