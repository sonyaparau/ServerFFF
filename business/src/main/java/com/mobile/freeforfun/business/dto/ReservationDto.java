package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.EReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto implements Serializable {

	private Long id;
	private UserDto user;
	private LocalDto local;
	private Integer numberOfPlaces;
	private Date dateReservation;
	private LocalDateTime dateCreation;
	private Time hourReservation;
	private EReservationType reservationType;
}
