package com.mobile.freeforfun.business.mapper;
import com.mobile.freeforfun.business.dto.TableTypeDto;
import com.mobile.freeforfun.persistence.model.TableType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TableTypeMapperImpl implements TableTypeMapper {

	@Override public TableTypeDto toDto(TableType tableType) {
		return TableTypeDto.builder()
				.id(tableType.getId())
				.numberOfPlaces(tableType.getNumberOfPlaces())
				.build();
	}

	@Override public TableType toEntity(TableTypeDto tableTypeDto) {
		return TableType.builder()
				.id(tableTypeDto.getId())
				.numberOfPlaces(tableTypeDto.getNumberOfPlaces())
				.build();
	}

	@Override public List<TableTypeDto> toDtoList(List<TableType> allTableTypes) {
		List<TableTypeDto> tableTypeDtos = new ArrayList<>();
		allTableTypes.forEach(tableType ->
				tableTypeDtos.add(toDto(tableType))
		);
		return tableTypeDtos;
	}

	@Override public List<TableType> toEntityList(List<TableTypeDto> allTableTypeDtos) {
		List<TableType> tableTypes = new ArrayList<>();
		allTableTypeDtos.forEach(tableTypeDto ->
				tableTypes.add(toEntity(tableTypeDto)));
		return tableTypes;
	}
}
