package com.mobile.freeforfun.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mobile.freeforfun.persistence.enums.EReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto implements Serializable {

	private Long id;
	private UserDto user;
	private LocalDto local;
	private Integer numberOfPlaces;
	private LocalTableDto table;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateTimeReservation;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateTimeCreation;
	private EReservationType reservationType;
	private Timestamp dateTimeLeave;
}
