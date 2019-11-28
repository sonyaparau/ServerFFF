package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.persistence.model.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocalMapperImpl implements LocalMapper {

	private UserMapper userMapper;

	@Autowired
	public LocalMapperImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override public LocalDto toDto(Local local) {
		return LocalDto.builder()
				.id(local.getId())
				.name(local.getName())
				.location(local.getLocation())
				.mobileNumber(local.getMobileNumber())
				.timetable(local.getTimetable())
				.rating(local.getRating())
				.description(local.getDescription())
				.smokingRestriction(local.getSmokingRestriction())
				.petRestriction(local.getPetRestriction())
				.wifi(local.getWifi())
				.type(local.getType())
				.owner(userMapper.toDto(local.getOwner()))
				.build();
	}

	@Override public Local toEntity(LocalDto localDto) {
		return Local.builder()
				.id(localDto.getId())
				.name(localDto.getName())
				.location(localDto.getLocation())
				.mobileNumber(localDto.getMobileNumber())
				.timetable(localDto.getTimetable())
				.rating(localDto.getRating())
				.description(localDto.getDescription())
				.smokingRestriction(localDto.getSmokingRestriction())
				.petRestriction(localDto.getPetRestriction())
				.wifi(localDto.getWifi())
				.type(localDto.getType())
				.owner(userMapper.toEntity(localDto.getOwner()))
				.build();
	}

	@Override public List<LocalDto> toDtoList(List<Local> allLocals) {
		List<LocalDto> localDtos = new ArrayList<>();
		allLocals.forEach(local ->
			localDtos.add(toDto(local))
		);
		return localDtos;
	}

	@Override public List<Local> toEntityList(List<LocalDto> allLocalDtos) {
		List<Local> locals = new ArrayList<>();
		allLocalDtos.forEach(localDto ->
				locals.add(toEntity(localDto)));
		return locals;
	}
}
