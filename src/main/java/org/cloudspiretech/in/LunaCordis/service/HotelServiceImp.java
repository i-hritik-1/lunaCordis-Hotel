package org.cloudspiretech.in.LunaCordis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelInfoDto;
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
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;


    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name.{}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel  = hotelRepository.save(hotel);
        log.info("Hotel has been created with id {}", hotel.getId());
        return modelMapper.map(hotel,HotelDto.class);

    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with id.{}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " not found."));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public List<HotelDto> getAllHotels()
    {
        List<Hotel> allHotels = hotelRepository.findAll();

        return allHotels.stream()
                .map((hotel) -> modelMapper.map(hotel,HotelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + hotelId + " not found."));

        List<RoomDto> rooms = hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .toList();

        return new HotelInfoDto(modelMapper.map(hotel, HotelDto.class),rooms);
    }


    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto)
    {
        log.info("Updating hotel with id.{}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " not found."));

//        hotel.setId(id);
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);

    }

    @Override
    @Transactional
    public void deleteHotelById(Long id)
    {
        log.info("Deleting hotel with id.{}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " not found."));

        hotelRepository.deleteById(id);

//        Deleting future inventory for the room

        for (Room room : hotel.getRooms()) {
            inventoryService.deleteFutureInventory(room);
            roomRepository.deleteById(room.getId());
        }

    }

    @Override
    public void activateHotel(Long id) {
        log.info("Activating hotel with id.{}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " not found."));

        hotel.setActive(true);
        hotelRepository.save(hotel);

        // Assuming only do it once
        for (Room room : hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }


}
