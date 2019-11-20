package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.ERoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoWithPicture implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String username;
    private ERoleType role;
    private Blob picture;
}
