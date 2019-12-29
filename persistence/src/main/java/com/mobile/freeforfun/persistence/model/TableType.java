package com.mobile.freeforfun.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tableTypes")
public class TableType {

	private static final String ID_TABLE_TYPE_COLUMN = "id_table_type";
	private static final String NUMBER_PLACES_COLUMN = "numer_places";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_TABLE_TYPE_COLUMN)
	private Long id;

	@Column(name = NUMBER_PLACES_COLUMN)
	private Integer numberOfPlaces;
}
