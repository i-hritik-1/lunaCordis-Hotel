package org.cloudspiretech.in.LunaCordis.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cloudspiretech.in.LunaCordis.entity.enums.BookingStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "booking")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Booking {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hotel_id", nullable = false)
//    private Hotel hotel;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Column(nullable = false)
//    private Integer roomCount;
//
//    @Column(nullable = false)
//    private LocalDate checkInDate;
//
//    @Column(nullable = false)
//    private LocalDate checkOutDate;
//
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private BookingStatus  bookingStatus;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "booking_guests",
//            joinColumns =  @JoinColumn(name = "booking_id"),
//            inverseJoinColumns = @JoinColumn(name = "guest_id")
//    )
//    private Set<Guest> guests;
//
//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal amount;
//}

@Entity
@Getter
@Setter
@Table(name = "booking")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer roomCount;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Guest> guests = new HashSet<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
