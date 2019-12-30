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
public class LocalTableDto implements Serializable {
	private Long id;
	private Integer numberOfPlaces;
	private LocalDto local;
}
