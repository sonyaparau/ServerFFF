package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.persistence.model.Local;

import java.util.List;

public interface LocalMapper {
	LocalDto toDto(Local local);
	Local toEntity(LocalDto localDto);
	List<LocalDto> toDtoList(List<Local> allLocals);
	List<Local> toEntityList(List<LocalDto> allLocalDtos);
}
