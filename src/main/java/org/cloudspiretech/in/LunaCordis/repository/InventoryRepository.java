package org.cloudspiretech.in.LunaCordis.repository;

import jakarta.persistence.LockModeType;
import org.cloudspiretech.in.LunaCordis.entity.Hotel;
import org.cloudspiretech.in.LunaCordis.entity.Inventory;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);

    void deleteByRoom(Room room);

    @Query("""
           SELECT DISTINCT i.hotel
           FROM Inventory i
           WHERE i.city = :city
                  AND i.date BETWEEN :startDate AND :endDate
                  AND i.closed = false
                  AND (i.totalCount - i.bookedCount) >= :roomsCount
           GROUP BY i.hotel, i.room
           HAVING COUNT(i.date) = :dateCount
           """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startSate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") long dateCount,
            Pageable pageable
    );

    @Query(
            """
            SELECT i
            FROM Inventory i
                        WHERE i.room.id = :roomId
                        AND i.date BETWEEN :startDate AND :endDate
                        AND i.closed = false
                        AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomsCount
            """
    )
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startSate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount
    );
}
