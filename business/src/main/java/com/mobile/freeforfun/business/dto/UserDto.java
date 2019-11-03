package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.model.Role;
import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String username;
    private Role role;
}
