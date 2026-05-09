package org.cloudspiretech.in.LunaCordis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.RoomDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.cloudspiretech.in.LunaCordis.exception.ResourceNotFoundException;
import org.cloudspiretech.in.LunaCordis.repository.HotelRepository;
import org.cloudspiretech.in.LunaCordis.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImp implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(RoomDto roomDto, Long hotelId) {

        log.info("Creating room in hotel with hotel id {}", hotelId);

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel Not found with id " + hotelId));


        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if (hotel.getActive())
        {
            inventoryService.initializeRoomForAYear(room);
        }
        // Create inventory for it as soon as the room is created and if hotel is active

        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto) {
        return null;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("Getting room with id {}", id);

        Room room = roomRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Room Not Found with id " + id));

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long id) {
        log.info("Deleting room with id {}", id);

         boolean exists = roomRepository.existsById(id);
         Room room = roomRepository.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("Room Not Found with id " + id));

         if(!exists){
             throw new ResourceNotFoundException("Room Not Found with id " + id);
         }

         inventoryService.deleteFutureInventory(room);
         roomRepository.deleteById(id);


    }

    @Override
    public List<RoomDto> getAllRoomsOfHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with hotel id {}", hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel Not found with id " + hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }
}
