package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.ReservationDto;
import com.mobile.freeforfun.persistence.model.Reservation;

import java.util.List;

public interface ReservationMapper {
	ReservationDto toDto(Reservation reservation);
	Reservation toEntity(ReservationDto reservationDto);
	List<ReservationDto> toDtoList(List<Reservation> allReservations);
	List<Reservation> toEntityList(List<ReservationDto> allReservationDtos);
}
