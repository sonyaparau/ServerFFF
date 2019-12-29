package com.mobile.freeforfun.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableTypeDto implements Serializable {

	private Long id;
	private Integer numberOfPlaces;
}
