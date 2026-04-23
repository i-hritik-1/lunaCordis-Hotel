package org.cloudspiretech.in.LunaCordis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cloudspiretech.in.LunaCordis.entity.enums.Gender;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

//    @ManyToMany
//    private Set<Booking> bookings;
}
