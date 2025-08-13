package com.acropolis.entity;

import com.acropolis.models.ExpenseLimterModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseLimiters {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer limiterid;
	@Column(nullable = false)
	private String title;	
	private String limits;
	@OneToOne
	@JoinColumn(name="customerid")
	private Users user;
	public ExpenseLimiters(String title, String limits, Users user) {
		super();
		this.title = title;
		this.limits = limits;
		this.user = user;
	}
	
	public ExpenseLimiters(ExpenseLimterModel model)
	{
		this.title=model.getTitle();
		this.limits=model.getLimits();
		 
		
	}
	
	
	
}
