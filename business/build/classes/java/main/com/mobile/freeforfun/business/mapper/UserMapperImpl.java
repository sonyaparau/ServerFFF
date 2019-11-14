package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.persistence.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-12T22:04:18+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 10.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

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
