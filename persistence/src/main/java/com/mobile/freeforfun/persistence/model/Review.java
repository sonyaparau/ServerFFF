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
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reviews")
public class Review {

	private static final String ID_REVIEW_COLUMN = "id_review";
	private static final String ID_USER_COLUMN = "id_user";
	private static final String ID_LOCAL_COLUMN = "id_local";
	private static final String DESCRIPTION_COLUMN = "description";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = ID_REVIEW_COLUMN)
	private Long id;

	@ManyToOne
	@JoinColumn(name = ID_USER_COLUMN)
	private User user;

	@NotNull(message = "Local cannot be null.")
	@ManyToOne
	@JoinColumn(name = ID_LOCAL_COLUMN)
	private Local local;

	@NotNull(message = "Description cannot be null.")
	@Column(name = DESCRIPTION_COLUMN)
	private String description;
}
