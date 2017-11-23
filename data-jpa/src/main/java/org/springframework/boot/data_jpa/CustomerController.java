package org.springframework.boot.data_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser(@RequestParam String firstname,
			@RequestParam String lastname) {
		
		Customer c = new Customer(firstname, lastname);
		customerRepository.save(c);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Customer> getAllCustomers(){
		Iterable<Customer> customers = customerRepository.findAll();
		return customerRepository.findAll();
	}
	
}
