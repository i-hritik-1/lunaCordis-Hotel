package org.cloudspiretech.in.LunaCordis.service;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.dto.RoomDto;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.cloudspiretech.in.LunaCordis.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {

    private final RoomRepository roomRepository;
    private ModelMapper modelMapper;
    @Override
    public RoomDto createNewRoom(RoomDto roomDto) {
        if(roomDto == null)
        {
            return null;
        }

        Room room = modelMapper.map(roomDto, Room.class);
        roomRepository.save(room);

        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto) {
        return null;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        return null;
    }

    @Override
    public void deleteRoomById(Long id) {

    }

    @Override
    public List<RoomDto> getAllRoomsOfHotel(Long hotelId) {
        return List.of();
    }
}
