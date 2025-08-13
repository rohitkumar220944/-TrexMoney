package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimterModel;
import com.acropolis.models.SearchModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.UserRepository;
import com.acropolis.services.ExpenseLimitersService;
import com.acropolis.services.TransactionService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("/auth/cust")
public class CustomerController {

	 private final UserRepository userRepository;
	 
	    private final TransactionService tser;
	    private final ExpenseLimitersService eser;

	    // ✅ Constructor injection
	    @Autowired
	    public CustomerController(UserRepository userRepository,
	                              TransactionService tser,
	                              ExpenseLimitersService eser) {
	        this.userRepository = userRepository;
	        this.tser = tser;
	        this.eser = eser;
}
	
	
	    
	@GetMapping("/home")
	public String homepage()
	{
		return "Welcome to Customer page";
	}
	
	@PostMapping("/savetrans")
	public ResponseEntity<APIResponse> save(@RequestBody TransactionModel model)
	{
		Transaction tr=tser.save(model);
		System.out.println(model);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transaction Saved..",true ,tr));
		else
			return  ResponseEntity.ok(new APIResponse("Transaction Saved..",false ,null));
	}
	
	@GetMapping("/gettrans/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transacation found..", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@GetMapping("/translist")
	public ResponseEntity<APIResponse> list(){
		Users userob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Transaction> list= tser.list(userob);
		if(list!=null)
			return ResponseEntity.ok(new APIResponse("Transacation List..", true,list));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@PutMapping("/updtrans/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id,@RequestBody Transaction model){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tser.update(model);
			tr=tser.update(tr);
			return ResponseEntity.ok(new APIResponse("Transacation updated..", true, tr));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be update...", false, null));
	}
	
	
	@DeleteMapping("/deltrans/{id}")
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
	public ResponseEntity<APIResponse>savelimit(@RequestBody ExpenseLimterModel model)
	{
		System.out.println(model);
		ExpenseLimiters exp=eser.save(model);
		if(exp!=null)
		{
			return ResponseEntity.ok(new APIResponse("Expense Limiter is set now",true ,exp));
		}
		else {
			return ResponseEntity.ok(new APIResponse("Expense limiter not set...", false, null));
		}
	}
	
	 @GetMapping("/getlimit")
	    public ResponseEntity<APIResponse> getlimiter() {
	    	ExpenseLimiters exp = eser.get();
	        if (exp != null) {
	            return ResponseEntity.ok(new APIResponse("Expense Limiter found", true, exp));
	        } else {
	            return ResponseEntity.ok(new APIResponse("Expense limiter not found...", false, null));
	        }
	    }
	 
//	 @PutMapping("/updlimit/{id}/{limit}")
//	    public ResponseEntity<APIResponse> update(@PathVariable("id") int id ,@PathVariable("limit") int limit) {
//	    	ExpenseLimiters exp = eser.get(id);
//	        if (exp != null) {
//	        	eser.update(exp);
//	        	exp = eser.update(exp);
//	            return ResponseEntity.ok(new APIResponse("Expense Limiter found", true, exp));
//	        } else {
//	            return ResponseEntity.ok(new APIResponse("Expense limiter not found...", false, null));
//	        }
//	    }
	 @DeleteMapping("/dellimit/{id}")
	    public ResponseEntity<APIResponse> deletelimiter(@PathVariable("id") int id) {
	    	ExpenseLimiters exp = eser.get(id);
	        if (exp != null) {
	        	eser.delete(exp);
	            return ResponseEntity.ok(new APIResponse("Expense Limiter found", true, exp));
	        } else {
	            return ResponseEntity.ok(new APIResponse("Expense limiter not found...", false, null));
	        }
	    }
	 
	 
	 @PostMapping("/searchtrans")
	 public ResponseEntity<APIResponse> search(@RequestBody SearchModel model)
	 {
	     	 List<Transaction>tr=tser.search(model);
				 
				 if(tr!=null) {
						
						return ResponseEntity.ok(new APIResponse("Transacation found..", true,tr));
					}
					else {
						return ResponseEntity.ok(new APIResponse("Transacation could not be found...", false, null));
				}
	 }

}
