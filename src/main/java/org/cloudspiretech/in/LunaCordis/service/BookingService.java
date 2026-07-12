package org.cloudspiretech.in.LunaCordis.service;

import org.cloudspiretech.in.LunaCordis.dto.BookingDto;
import org.cloudspiretech.in.LunaCordis.dto.BookingRequestDto;
import org.cloudspiretech.in.LunaCordis.dto.GuestDto;

import java.util.List;

public interface BookingService {


    BookingDto initiateBooking(BookingRequestDto bookingRequestDto);

    BookingDto addGuest(long bookingId, List<GuestDto> guestDto);
}
