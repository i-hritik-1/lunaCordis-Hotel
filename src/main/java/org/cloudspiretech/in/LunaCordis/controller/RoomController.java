package org.cloudspiretech.in.LunaCordis.controller;

import lombok.RequiredArgsConstructor;
import org.cloudspiretech.in.LunaCordis.dto.RoomDto;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import org.cloudspiretech.in.LunaCordis.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create/{hotelId}")
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto)
    {
        RoomDto roomDto1 = roomService.createNewRoom(roomDto, hotelId);

        return new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }

    @GetMapping("/allRooms/{hotelId}")
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId)
    {
        List<RoomDto> roomDtoList = roomService.getAllRoomsOfHotel(hotelId);

        return ResponseEntity.ok(roomDtoList);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId)
    {
        RoomDto roomDto = roomService.getRoomById(roomId);

        return ResponseEntity.ok(roomDto);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId)
    {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

}
