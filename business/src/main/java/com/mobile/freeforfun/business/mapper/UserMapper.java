package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.persistence.model.User;
import java.util.List;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> allUsers);
    List<User> toEntityList(List<UserDto> allUserDtos);
}
