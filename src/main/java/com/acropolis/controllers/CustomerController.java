package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Transaction;
import com.acropolis.models.ExpenseLimterModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.UserRepository;
import com.acropolis.services.TransactionService;
import com.acropolis.util.APIResponse;

@RestController
@RequestMapping("/auth/cust")
public class CustomerController {

    private final UserRepository userRepository;
	@Autowired
	private TransactionService tser;

    CustomerController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	@GetMapping("/home")
	public String homepage()
	{
		return "Welcome to Customer page";
	}
	
	@PostMapping("/save")
	public ResponseEntity<APIResponse> save(@RequestBody TransactionModel model)
	{
		Transaction tr=tser.save(model);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transaction Saved..",true ,tr));
		else
			return  ResponseEntity.ok(new APIResponse("Transaction Saved..",false ,null));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transacation found..", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@GetMapping("/list")
	public ResponseEntity<APIResponse> list(){
		List<Transaction> list= tser.list();
		if(list!=null)
			return ResponseEntity.ok(new APIResponse("Transacation List..", true,list));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@PutMapping("/upd/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id,@RequestBody TransactionModel model){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tr.update(model);
			tr=tser.update(tr);
			return ResponseEntity.ok(new APIResponse("Transacation updated..", true, tr));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be update...", false, null));
	}
	
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<APIResponse> delete(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tser.delete(tr);
			return ResponseEntity.ok(new APIResponse("Transacation removed..", true,null));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be remove...", false, null));
	}
	
	
	@PostMapping("/savelimit")
	public ResponseEntity<APIResponse>savelimit(@RequestBody ExpenseLimterModele model)
	{
		ExpenseLimiters exp=
	}
	

}
