package org.cloudspiretech.in.LunaCordis.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.cloudspiretech.in.LunaCordis.entity.enums.Role;

import java.util.Set;

@Data
public class UserDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> role;

}
