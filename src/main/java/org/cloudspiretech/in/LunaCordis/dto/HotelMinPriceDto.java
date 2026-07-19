package org.cloudspiretech.in.LunaCordis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class HotelMinPriceDto {

    private Long id;
    private Hotel hotel;
    private double price;
    private LocalDate date;
}
