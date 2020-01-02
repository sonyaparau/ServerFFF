package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.persistence.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserDtoWithPicture toDtoPicture(User user) {
        UserDtoWithPicture userDtoWithPicture = new UserDtoWithPicture();
        userDtoWithPicture.setId( user.getId() );
        userDtoWithPicture.setFirstName( user.getFirstName() );
        userDtoWithPicture.setLastName( user.getLastName() );
        userDtoWithPicture.setPassword( user.getPassword() );
        userDtoWithPicture.setEmail( user.getEmail() );
        userDtoWithPicture.setMobileNumber( user.getMobileNumber() );
        userDtoWithPicture.setUsername( user.getUsername() );
        userDtoWithPicture.setRole( user.getRole() );
        userDtoWithPicture.setPicture( user.getPicture() );
        return userDtoWithPicture;
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .username(userDto.getUsername())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> allUsers) {
        return null;
    }

    @Override
    public List<User> toEntityList(List<UserDto> allUserDtos) {
        return null;
    }

    @Override
    public List<UserDtoWithPicture> toDtoPictureList(List<UserDtoWithPicture> userDtoWithPictures) {
        return null;
    }
}
