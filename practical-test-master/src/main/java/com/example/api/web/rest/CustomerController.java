package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity findAll(@RequestParam(value ="page", defaultValue = "0") Integer page,
								  @RequestParam(value ="size", defaultValue = "10") Integer size) {
		List<Customer> customers = service.findAll(PageRequest.of(page,size));

		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Customer customer){
		Customer c = service.save(customer);
		return c !=null ?
				ResponseEntity.ok("Id-" + c.getId() + " Inserido") :
				ResponseEntity.badRequest().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Customer customer){
		customer.setId(id);
		Customer c = service.update(customer, id);
		return c != null ?
				ResponseEntity.ok("Id:-" + c.getId() + " Atualizado") :
				ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id){
		boolean ok = service.delete(id);
		return ok ?
				ResponseEntity.ok("Id:-"+ id + " Excluido") :
				ResponseEntity.notFound().build();
	}

}
