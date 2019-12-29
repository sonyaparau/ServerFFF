package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.ReservationDto;
import com.mobile.freeforfun.persistence.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationMapperImpl implements ReservationMapper{

	private UserMapper userMapper;
	private LocalMapper localMapper;

	@Autowired
	public ReservationMapperImpl(UserMapper userMapper, LocalMapper localMapper) {
		this.userMapper = userMapper;
		this.localMapper = localMapper;
	}

	@Override public ReservationDto toDto(Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.user(userMapper.toDto(reservation.getUser()))
				.local(localMapper.toDto(reservation.getLocal()))
				.numberOfPlaces(reservation.getNumberOfPlaces())
				.dateReservation(reservation.getDateReservation())
				.dateCreation(reservation.getDateCreation())
				.hourReservation(reservation.getHourReservation())
				.reservationType(reservation.getReservationType())
				.build();
	}

	@Override public Reservation toEntity(ReservationDto reservationDto) {
		return Reservation.builder()
				.id(reservationDto.getId())
				.user(userMapper.toEntity(reservationDto.getUser()))
				.local(localMapper.toEntity(reservationDto.getLocal()))
				.numberOfPlaces(reservationDto.getNumberOfPlaces())
				.dateReservation(reservationDto.getDateReservation())
				.dateCreation(reservationDto.getDateCreation())
				.hourReservation(reservationDto.getHourReservation())
				.reservationType(reservationDto.getReservationType())
				.build();
	}

	@Override public List<ReservationDto> toDtoList(List<Reservation> allReservations) {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		allReservations.forEach(reservation ->
				reservationDtos.add(toDto(reservation))
		);
		return reservationDtos;
	}

	@Override public List<Reservation> toEntityList(List<ReservationDto> allReservationDtos) {
		List<Reservation> reservations = new ArrayList<>();
		allReservationDtos.forEach(reservationDto ->
				reservations.add(toEntity(reservationDto)));
		return reservations;
	}
}
