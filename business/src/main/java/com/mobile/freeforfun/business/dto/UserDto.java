package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.ERoleType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String username;
    private ERoleType role;
}
