package org.cloudspiretech.in.LunaCordis.repository;

import org.cloudspiretech.in.LunaCordis.dto.HotelMinPriceDto;
import org.cloudspiretech.in.LunaCordis.entity.HotelMinPrice;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice, Long> {

    @Query("""
            SELECT org.cloudspiretech.in.LunaCordis.dto.HotelMinPriceDto(i.hotel, AVG(i.price))
            FROM HotelMinPrice i
            WHERE i.hotel.city = city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.hotel.active = true
            GROUP BY i.hotel
            """)
    Page<HotelMinPriceDto> findHotelMinimumPrice(
            @Param("city") String city,
            @Param("startDate")LocalDate startDate,
            @Param("endDate")LocalDate endDate
            );
}
