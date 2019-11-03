package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.RoleDto;
import com.mobile.freeforfun.persistence.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role user);
    Role toEntity(RoleDto userDto);
    List<RoleDto> toDtoList(List<Role> allRoles);
    List<Role> toEntityList(List<RoleDto> allUserDtos);
}
