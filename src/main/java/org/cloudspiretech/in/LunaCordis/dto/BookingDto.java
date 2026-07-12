package org.cloudspiretech.in.LunaCordis.dto;

import lombok.Data;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.cloudspiretech.in.LunaCordis.entity.User;
import org.cloudspiretech.in.LunaCordis.entity.enums.BookingStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto
{
    private Long id;
    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private String roomType;
    private Long userId;
    private String userName;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}

//@Data
//public class BookingDto {
//
//    private Long id;
//
//    private Long hotelId;
//    private String hotelName;
//
//    private Long roomId;
//    private String roomType;
//
//    private Long userId;
//    private String userName;
//
//    private Integer roomCount;
//
//    private LocalDate checkInDate;
//    private LocalDate checkOutDate;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    private BookingStatus bookingStatus;
//
//    private Set<GuestDto> guests;
//}