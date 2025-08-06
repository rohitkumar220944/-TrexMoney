package com.acropolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimterModel;
import com.acropolis.repositories.ExpenseLimitersRepository;

@Service
public class ExpenseLimitersService {
	
	@Autowired
	private ExpenseLimitersRepository erep;
	
	public ExpenseLimiters save(ExpenseLimterModel model)
	{
		try {
			ExpenseLimiters exp=new ExpenseLimiters(model);
			Users userob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			exp.setUser(userob);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public ExpenseLimiters get(int id)
	
	
}
