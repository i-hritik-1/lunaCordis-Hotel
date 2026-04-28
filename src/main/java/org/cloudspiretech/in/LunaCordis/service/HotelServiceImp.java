package org.cloudspiretech.in.LunaCordis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.cloudspiretech.in.LunaCordis.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name.", hotelDto.getName());

        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);

        hotel  = hotelRepository.save(hotel);
        log.info("Hotel has been created with id ", hotel.getId());

        return modelMapper.map(hotel,HotelDto.class);

    }

    @Override
    public HotelDto getHotelById(Long id) {

        log.info("Getting hotel with id.", id);

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel with id " + id + " not found."));
        return modelMapper.map(hotel, HotelDto.class);
    }

}
