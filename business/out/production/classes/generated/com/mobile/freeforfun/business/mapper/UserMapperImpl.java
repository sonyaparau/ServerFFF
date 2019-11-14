package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.persistence.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-14T20:10:06+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

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
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setPassword( userDto.getPassword() );
        user.setEmail( userDto.getEmail() );
        user.setMobileNumber( userDto.getMobileNumber() );
        user.setUsername( userDto.getUsername() );
        user.setRole( userDto.getRole() );

        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> allUsers) {
        if ( allUsers == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( allUsers.size() );
        for ( User user : allUsers ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntityList(List<UserDto> allUserDtos) {
        if ( allUserDtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( allUserDtos.size() );
        for ( UserDto userDto : allUserDtos ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
