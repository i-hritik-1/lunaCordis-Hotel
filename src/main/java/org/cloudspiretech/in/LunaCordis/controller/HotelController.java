package org.cloudspiretech.in.LunaCordis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto)
    {
        log.info("Attempting to create a new hotel dto with name : {}", hotelDto.getName());

        HotelDto hotel = hotelService.createNewHotel(hotelDto);

        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id)
    {
        HotelDto hotel = hotelService.getHotelById(id);

        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto)
    {

        HotelDto hotelDto1 =  hotelService.updateHotelById(hotelId, hotelDto);

        return ResponseEntity.ok(hotelDto1);

    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId)
    {
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> activateHotel(@PathVariable Long id)
    {
        hotelService.activateHotel(id);

        return ResponseEntity.noContent().build();
    }
}
