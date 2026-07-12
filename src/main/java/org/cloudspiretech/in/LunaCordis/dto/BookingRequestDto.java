package org.cloudspiretech.in.LunaCordis.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

@Data
public class BookingRequestDto {
    private Long hotelId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numberOfRooms;
}
