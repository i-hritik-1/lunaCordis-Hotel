package org.cloudspiretech.in.LunaCordis.dto;


import lombok.Data;
import org.cloudspiretech.in.LunaCordis.entity.HotelContactInfo;
import org.cloudspiretech.in.LunaCordis.entity.Room;
import java.util.List;

@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private List<RoomDto> rooms;
    private Boolean active;

}
