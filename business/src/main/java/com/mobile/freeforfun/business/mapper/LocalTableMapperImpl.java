package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.LocalTableDto;
import com.mobile.freeforfun.persistence.model.LocalTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalTableMapperImpl implements LocalTableMapper{

	private LocalMapper localMapper;

	@Autowired
	public LocalTableMapperImpl(LocalMapper localMapper) {
		this.localMapper = localMapper;
	}

	@Override public LocalTableDto toDto(LocalTable localTable) {
		return LocalTableDto.builder()
				.id(localTable.getId())
				.local(localMapper.toDto(localTable.getLocal()))
				.build();
	}

	@Override public LocalTable toEntity(LocalTableDto localTableDto) {
		return LocalTable.builder()
				.id(localTableDto.getId())
				.local(localMapper.toEntity(localTableDto.getLocal()))
				.build();
	}

	@Override public List<LocalTableDto> toDtoList(List<LocalTable> allTables) {
		List<LocalTableDto> tableDtos = new ArrayList<>();
		allTables.forEach(table ->
				tableDtos.add(toDto(table))
		);
		return tableDtos;
	}

	@Override public List<LocalTable> toEntityList(List<LocalTableDto> allTableDtos) {
		List<LocalTable> tables = new ArrayList<>();
		allTableDtos.forEach(tableDto ->
				tables.add(toEntity(tableDto)));
		return tables;
	}
}
