package com.acropolis.services;

import java.time.LocalDate;
import java.util.List;
import com.acropolis.repositories.UserRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.models.SearchModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.TransactionRepository;

@Service
public class TransactionService {

    private final UserRepository userRepository;

	@Autowired
	private TransactionRepository trep;

    TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public Transaction save(TransactionModel model)
	{
		try {
			Transaction tr=new Transaction(model);
			tr.setCreated(LocalDate.now());
			Users usersob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 tr.setUser(usersob);
			return trep.save(tr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public List<Transaction> list(Users userob) {
		try {
			return trep.findByUser(userob);
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

	public List<Transaction> search(SearchModel model) {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Transaction> list=null;
		if(model.getType()==null||model.getType().length()==0)
			list=trep.findByMonthANDYear(model.getMonth(),model.getYear(),user);
		else
			list=trep.findByMonthANDYearANDType(model.getMonth(),model.getYear(),model.getType(),user);
		return list;
	}

	

}
