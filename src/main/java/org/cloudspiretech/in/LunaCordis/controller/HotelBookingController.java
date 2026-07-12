package org.cloudspiretech.in.LunaCordis.controller;

import lombok.AllArgsConstructor;
import org.cloudspiretech.in.LunaCordis.dto.BookingDto;
import org.cloudspiretech.in.LunaCordis.dto.BookingRequestDto;
import org.cloudspiretech.in.LunaCordis.dto.GuestDto;
import org.cloudspiretech.in.LunaCordis.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/initiate")
    public ResponseEntity<BookingDto> initiateBooking(@RequestBody BookingRequestDto bookingRequestDto)
    {
        return ResponseEntity.ok(bookingService.initiateBooking(bookingRequestDto));
    }

    @PostMapping("{bookingId}/addGuest")
    public ResponseEntity<BookingDto> addGuest(@PathVariable long bookingId, @RequestBody List<GuestDto> guestDto)
    {
        return ResponseEntity.ok(bookingService.addGuest(bookingId,guestDto));
    }

}
