package com.ensat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {



	private String name;

	private String description;

	private Boolean isActive;
/*
	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
*/
}