package org.cloudspiretech.in.LunaCordis.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.cloudspiretech.in.LunaCordis.entity.User;
import org.cloudspiretech.in.LunaCordis.entity.enums.Gender;

@Data
public class GuestDto {

    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
