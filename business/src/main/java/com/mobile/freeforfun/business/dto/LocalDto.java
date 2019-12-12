package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.ELocalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalDto implements Serializable {

	private Long id;
	private String name;
	private String location;
	private String mobileNumber;
	private String timetable;
	private Float rating;
	private String description;
	private Boolean smokingRestriction;
	private Boolean petRestriction;
	private Boolean wifi;
	private UserDto owner;
	private ELocalType type;
}
