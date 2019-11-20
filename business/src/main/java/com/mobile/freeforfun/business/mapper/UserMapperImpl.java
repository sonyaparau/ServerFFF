package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.persistence.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPassword( user.getPassword() );
        userDto.setEmail( user.getEmail() );
        userDto.setMobileNumber( user.getMobileNumber() );
        userDto.setUsername( user.getUsername() );
        userDto.setRole( user.getRole() );
        return userDto;
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
        return null;
    }

    @Override
    public List<UserDto> toDtoList(List<User> allUsers) {
        return null;
    }

    @Override
    public List<User> toEntityList(List<UserDto> allUserDtos) {
        return null;
    }
}
