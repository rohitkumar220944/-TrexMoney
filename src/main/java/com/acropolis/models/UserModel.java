package com.acropolis.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
	@NotNull(message="Name can not be Null")
	@NotNull(message="Name can not be empty")
	private String name;
	
	@Email(message="Email pattern is not be correct..")
	private String email;
	@NotNull(message="Password can not be empty")
	private String password;

}
