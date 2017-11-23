package org.springframework.boot.data_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public @ResponseBody String addNewUser(@RequestParam String firstname,
			@RequestParam String lastname) {
		
		Customer c = new Customer(firstname, lastname);
		customerRepository.save(c);
		return "Saved";
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public @ResponseBody Iterable<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
}
