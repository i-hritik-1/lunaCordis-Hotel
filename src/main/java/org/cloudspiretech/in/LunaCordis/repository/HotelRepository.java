package org.cloudspiretech.in.LunaCordis.repository;

import org.cloudspiretech.in.LunaCordis.dto.HotelDto;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
