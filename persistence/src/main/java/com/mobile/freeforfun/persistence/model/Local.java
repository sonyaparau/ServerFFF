package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.ELocalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Local {

	private static final String ID_LOCAL_COLUMN = "id_local";
	private static final String NAME_COLUMN = "name";
	private static final String LOCATION_COLUMN = "location";
	private static final String MOBILE_NUMBER_COLUMN = "mobile_number";
	private static final String TIMETABLE_COLUMN = "timetable";
	private static final String RATING_COLUMN = "rating";
	private static final String DESCRIPTION_COLUMN = "description";
	private static final String SMOKING_RESTRICTION_COLUMN = "smoking_restriction";
	private static final String PET_RESTRICTION_COLUMN = "pet_restriction";
	private static final String WIFI_COLUMN = "wifi";
	private static final String ID_USER_COLUMN = "id_user";
	private static final String TYPE_COLUMN = "type";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_LOCAL_COLUMN)
	private Long id;

	@NotNull(message = "Name cannot be null.")
	@Column(name = NAME_COLUMN)
	private String name;

	@Column(name = LOCATION_COLUMN)
	private String location;

	@Column(name = MOBILE_NUMBER_COLUMN)
	private String mobileNumber;

	@Column(name = TIMETABLE_COLUMN)
	private String timetable;

	@Column(name = RATING_COLUMN)
	private Float rating;

	@Column(name = DESCRIPTION_COLUMN)
	private String description;

	@Column(name = SMOKING_RESTRICTION_COLUMN)
	private Boolean smokingRestriction;

	@Column(name = PET_RESTRICTION_COLUMN)
	private Boolean petRestriction;

	@Column(name = WIFI_COLUMN)
	private Boolean wifi;

	@NotNull(message = "Owner cannot be null.")
	@ManyToOne
	@JoinColumn(name = ID_USER_COLUMN)
	private User owner;

	@Column(name = TYPE_COLUMN)
	@Enumerated(EnumType.ORDINAL)
	private ELocalType type;
}
