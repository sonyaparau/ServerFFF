package com.mobile.freeforfun.business.restControllers;

import com.mobile.freeforfun.business.dto.ReservationDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.service.ReservationServiceImpl;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RequestMapping(ApiEndpoints.APPLICATION_NAME)
@RestController
public class ReservationController {

	private ReservationServiceImpl reservationService;

	@Autowired
	public ReservationController(ReservationServiceImpl reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping(value = ApiEndpoints.CREATE_RESERVATION,
			produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity createReservation(@RequestBody ReservationDto reservationDto){
		try{
			Timestamp creationTime = new Timestamp(reservationDto.getDateTimeCreation()
					.getTime() - (3600000*2));
			Timestamp reservationTime =  new Timestamp(reservationDto.getDateTimeReservation()
					.getTime() - (3600000*2));
			reservationDto.setDateTimeCreation(creationTime);
			reservationDto.setDateTimeReservation(reservationTime);
			reservationService.saveReservation(reservationDto);
			return new ResponseEntity<>("Reservation successfully created!", HttpStatus.OK);
		} catch(BusinessException exception){
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping(value = ApiEndpoints.DELETE_RESERVATION,
			produces = APPLICATION_JSON_VALUE)
	public ResponseEntity deleteReservation(@PathVariable("id") Long id){
		try{
			reservationService.deleteReservation(id);
			return new ResponseEntity<>("Reservation was successfully deleted!", HttpStatus.OK);
		} catch(BusinessException exception){
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.CREATED);
		}
	}
}
