package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.LocalTableDto;
import com.mobile.freeforfun.persistence.model.LocalTable;

import java.util.List;

public interface LocalTableMapper {
	LocalTableDto toDto(LocalTable localTable);
	LocalTable toEntity(LocalTableDto localTableDto);
	List<LocalTableDto> toDtoList(List<LocalTable> allTables);
	List<LocalTable> toEntityList(List<LocalTableDto> allTableDtos);
}
