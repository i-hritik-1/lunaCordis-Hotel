package org.cloudspiretech.in.LunaCordis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.BookingDto;
import org.cloudspiretech.in.LunaCordis.dto.BookingRequestDto;
import org.cloudspiretech.in.LunaCordis.dto.GuestDto;
import org.cloudspiretech.in.LunaCordis.entity.*;
import org.cloudspiretech.in.LunaCordis.entity.enums.BookingStatus;
//import org.cloudspiretech.in.LunaCordis.entity.enums.Gender;
import org.cloudspiretech.in.LunaCordis.exception.ResourceNotFoundException;
import org.cloudspiretech.in.LunaCordis.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService{

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final GuestRepository guestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto initiateBooking(BookingRequestDto bookingRequestDto) {

        log.info("initiateBooking");
        Hotel hotel = hotelRepository.findById(bookingRequestDto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + bookingRequestDto.getHotelId()));

        Room room = roomRepository.findById(bookingRequestDto.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id: " + bookingRequestDto.getRoomId()));


        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(
                room.getId(), bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate(),bookingRequestDto.getNumberOfRooms()
        );

        long daysOfCount = ChronoUnit.DAYS.between(bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate()) + 1;

        if (inventoryList.size() != daysOfCount)
        {
            throw new IllegalStateException("Room is not available anymore");
        }

        // reserve the room and update the inventory

        for(Inventory inventory : inventoryList)
        {
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequestDto.getNumberOfRooms());

        }

        inventoryRepository.saveAll(inventoryList);


//        Create booking for the user

        User user = userRepository.findById(1L).
                orElseThrow(
                        ()-> new ResourceNotFoundException("User not found with id: " + 1L)
                );


//        TODO : ADD THE DYNAMIC PRICING IN FUTURE

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequestDto.getCheckInDate())
                .checkOutDate(bookingRequestDto.getCheckOutDate())
                .roomCount(bookingRequestDto.getNumberOfRooms())
                .user(user)
                .amount(BigDecimal.TEN)
                .build();

        bookingRepository.save(booking);
        
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    @Transactional
    public BookingDto addGuest(long bookingId, List<GuestDto> guestDto) {
        log.info("Adding guest to the booking");
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Booking not found with id: " + bookingId)
                );

        if(hasBookingExpired(booking))
        {
            throw new IllegalStateException("Booking has expired. Please initiate a new booking.");
        }

        if (booking.getBookingStatus() != BookingStatus.RESERVED)
        {
            throw new IllegalStateException("Booking is not in reserved state. Cannot add guests.");
        }


        int maxGuests = booking.getRoomCount() * booking.getRoom().getCapacity();
        if (guestDto.size() > maxGuests)
        {
            throw new IllegalStateException(
                    "Guest count exceeds room capacity"
            );
        }

//        private User user;
//        private String name;
//        private Gender gender;
//        private Integer age;

        for (GuestDto dto : guestDto)
        {
            Guest guest = modelMapper.map(dto, Guest.class);
            guest.setUser(booking.getUser());
            guest.setBooking(booking);
            booking.getGuests().add(guest);
        }

        booking.setBookingStatus(BookingStatus.GUEST_ADDED);
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDto.class);
    }

    public Boolean hasBookingExpired(Booking booking)
    {
        log.info("Checking if booking has expired");
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }


}
