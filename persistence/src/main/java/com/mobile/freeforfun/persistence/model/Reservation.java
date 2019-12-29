package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.EReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reservations")
public class Reservation {

	private static final String ID_RESERVATION_COLUMN = "id_reservation";
	private static final String ID_USER_COLUMN = "id_user";
	private static final String ID_LOCAL_COLUMN = "id_local";
	private static final String NUMBER_PLACES_COLUMN = "number_places";
	private static final String DATE_RESERVATION_COLUMN = "date_reservation";
	private static final String DATE_CREATION_COLUMN = "date_creation";
	private static final String HOUR_RESERVATION_COLUMN = "hour_reservation";
	private static final String TYPE_RESERVATION_COLUMN = "type_reservation";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_RESERVATION_COLUMN)
	private Long id;

	@ManyToOne
	@JoinColumn(name = ID_USER_COLUMN)
	private User user;

	@ManyToOne
	@JoinColumn(name = ID_LOCAL_COLUMN)
	private Local local;

	@Column(name = NUMBER_PLACES_COLUMN)
	private Integer numberOfPlaces;

	@Column(name = DATE_RESERVATION_COLUMN)
	private Date dateReservation;

	@Column(name = DATE_CREATION_COLUMN)
	private LocalDateTime dateCreation;

	@Column(name = HOUR_RESERVATION_COLUMN)
	private Time hourReservation;

	@Column(name = TYPE_RESERVATION_COLUMN)
	private EReservationType reservationType;
}
