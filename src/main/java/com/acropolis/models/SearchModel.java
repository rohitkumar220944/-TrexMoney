package com.acropolis.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchModel {
	@NotNull(message="Month can not be blank")
	private Integer month;
	private Integer year;
	private String type;
	

}
