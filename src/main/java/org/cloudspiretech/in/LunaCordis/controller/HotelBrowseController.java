package org.cloudspiretech.in.LunaCordis.controller;



import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelInfoDto;
import org.cloudspiretech.in.LunaCordis.dto.HotelSearchRequestDto;
import org.cloudspiretech.in.LunaCordis.service.HotelService;
import org.cloudspiretech.in.LunaCordis.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequestDto hotelSearchRequestDto)
    {

        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequestDto);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId)
    {

        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));

    }


}
