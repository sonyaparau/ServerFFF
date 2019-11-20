package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.ERoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private static final String ID_USER_COLUMN = "id_user";
    private static final String FIRST_NAME_COLUMN = "firstname";
    private static final String LAST_NAME_COLUMN = "lastname";
    private static final String PASSWORD_COLUMN = "password";
    private static final String EMAIL_COLUMN = "email";
    private static final String MOBILE_NUMBER_COLUMN = "mobile_number";
    private static final String USERNAME_COLUMN = "username";
    private static final String ROLE_COLUMN = "role";
    private static final String PICTURE_COLUMN = "picture";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name=ID_USER_COLUMN)
    private Long id;

    @Column(name=FIRST_NAME_COLUMN)
    private String firstName;

    @Column(name=LAST_NAME_COLUMN)
    private String lastName;

    @NotNull(message = "Password cannot be null.")
    @Column(name=PASSWORD_COLUMN)
    private String password;

    @NotNull(message = "Email cannot be null.")
    @Column(name=EMAIL_COLUMN)
    private String email;

    @Column(name=MOBILE_NUMBER_COLUMN)
    private String mobileNumber;

    @NotNull(message = "Username cannot be null.")
    @Column(name=USERNAME_COLUMN)
    private String username;

    @Column(name = ROLE_COLUMN)
    @Enumerated(EnumType.ORDINAL)
    private ERoleType role;

    @Column(name=PICTURE_COLUMN)
    @Lob
    private Blob picture;
}
