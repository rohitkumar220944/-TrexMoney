package com.acropolis.services;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository trep;
	
	public Transaction save(TransactionModel model)
	{
		try {
			Transaction tr=new Transaction(model);
			tr.setCreated(LocalDate.now());
			Users usersob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return trep.save(tr);
		} catch (Exception e) {
			return null;
		}
	}

	public Transaction get(int id) {
		try {
			return trep.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}

	public List<Transaction> list() {
		try {
			return trep.findAll();
		}catch(Exception e) {
			return null;
		}
	}
	
	public Transaction update(Transaction tr) {
		return trep.save(tr);
	}

	public void delete(Transaction tr) {
		trep.delete(tr);
	}

	

}
