package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.persistence.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> allUsers);
    List<User> toEntityList(List<UserDto> allUserDtos);
}
