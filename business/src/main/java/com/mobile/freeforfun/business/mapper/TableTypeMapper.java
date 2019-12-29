package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.TableTypeDto;
import com.mobile.freeforfun.persistence.model.TableType;

import java.util.List;

public interface TableTypeMapper {
	TableTypeDto toDto(TableType tableType);
	TableType toEntity(TableTypeDto tableTypeDto);
	List<TableTypeDto> toDtoList(List<TableType> allTableTypes);
	List<TableType> toEntityList(List<TableTypeDto> allTableTypeDtos);
}
