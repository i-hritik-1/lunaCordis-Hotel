package org.cloudspiretech.in.LunaCordis.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cloudspiretech.in.LunaCordis.entity.enums.Gender;

import java.util.List;
import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "guest")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Guest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//    private Integer age;
//
////    @ManyToMany
////    private Set<Booking> bookings;
//}

@Entity
@Getter
@Setter
@Table(name = "guest")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
}
