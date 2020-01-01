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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tables")
public class LocalTable {

	private static final String ID_TABLE_COLUMN = "id_table";
	private static final String NUMBER_OF_PLACES_COLUMN = "number_of_places";
	private static final String ID_LOCAL_COLUMN = "id_local";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_TABLE_COLUMN)
	private Long id;

	@JoinColumn(name = NUMBER_OF_PLACES_COLUMN)
	private Integer numberOfPlaces;

	@ManyToOne
	@JoinColumn(name = ID_LOCAL_COLUMN)
	private Local local;
}
