package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.ReservationDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;

import java.util.List;

public interface ReservationService {
	void deleteReservation(Long id) throws BusinessException;
	ReservationDto saveReservation(ReservationDto reservationDto) throws BusinessException;
	List<ReservationDto> getReservationsByUser(Long userId);
}
