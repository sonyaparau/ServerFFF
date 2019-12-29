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
import javax.persistence.ManyToMany;
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
	private static final String FREE_COLUMN = "free";
	private static final String ID_LOCAL_COLUMN = "id_local";
	private static final String ID_TYPE_TABLE_COLUMN = "id_type_table";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_TABLE_COLUMN)
	private Long id;

	@Column(name = FREE_COLUMN)
	private Boolean free;

	@ManyToOne
	@JoinColumn(name = ID_LOCAL_COLUMN)
	private Local local;


}
