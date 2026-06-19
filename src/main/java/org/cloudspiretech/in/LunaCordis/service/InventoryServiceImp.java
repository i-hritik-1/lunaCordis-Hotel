package org.cloudspiretech.in.LunaCordis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelSearchRequestDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.cloudspiretech.in.LunaCordis.repository.InventoryRepository;
import org.cloudspiretech.in.LunaCordis.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImp implements InventoryService{

    private final RoomRepository roomRepository;
    private final InventoryRepository  inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for (; !today.isAfter(endDate); today = today.plusDays(1))
        {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();

            inventoryRepository.save(inventory);

        }

    }

    @Override
    public void deleteFutureInventory(Room room)
    {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequestDto hotelSearchRequestDto) {
        Pageable pageable = PageRequest.of(hotelSearchRequestDto.getPage(), hotelSearchRequestDto.getSize());
        long dateCount =
                ChronoUnit.DAYS.between(hotelSearchRequestDto.getStartDate(),hotelSearchRequestDto.getEndDate()) + 1;

        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory
                (
                        hotelSearchRequestDto.getCity(),
                        hotelSearchRequestDto.getStartDate(),
                        hotelSearchRequestDto.getEndDate(),
                        hotelSearchRequestDto.getRoomCount(),
                        dateCount,
                        pageable
                );

        return hotelPage.map((hotel) -> modelMapper.map(hotel,HotelDto.class));
    }

}
