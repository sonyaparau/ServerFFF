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
import java.sql.Timestamp;

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
	private static final String ID_TABLE_COLUMN = "id_table";
	private static final String DATE_TIME_RESERVATION_COLUMN = "date_time_reservation";
	private static final String DATE_CREATION_COLUMN = "date_creation";
	private static final String TYPE_RESERVATION_COLUMN = "type_reservation";
	private static final String DATE_TIME_LEAVE_COLUMN = "date_time_leave";

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

	@ManyToOne
	@JoinColumn(name = ID_TABLE_COLUMN)
	private LocalTable table;

	@Column(name = DATE_TIME_RESERVATION_COLUMN)
	private Timestamp dateTimeReservation;

	@Column(name = DATE_CREATION_COLUMN)
	private Timestamp dateCreation;

	@Column(name = TYPE_RESERVATION_COLUMN)
	private EReservationType reservationType;

	@Column(name = DATE_TIME_LEAVE_COLUMN)
	private Timestamp dateTimeLeave;
}
