package org.cloudspiretech.in.LunaCordis.repository;

import org.cloudspiretech.in.LunaCordis.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
