package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import org.springframework.util.Assert;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {this.repository = repository;}
	public List<Customer> findAll(Pageable pageable) {
		return repository.findAllByOrderByNameAsc(pageable);
	}
	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	public Customer save(Customer customer){
		Assert.isNull(customer.getId(),"Não foi possível inserir o registro");
		return repository.save(customer);
	}
	public Customer update(Customer customer, Long id){
		Optional<Customer> op = repository.findById(id);
		if(op.isPresent()){
			Customer db = op.get();
			db.setName(customer.getName());
			db.setEmail(customer.getEmail());
			db.setEndereco(customer.getEndereco());
			db.setEndereco2(customer.getEndereco2());
			db.setEndereco3(customer.getEndereco3());
			System.out.println("Customer id" + db.getId());

			repository.save(db);

			return customer;
		}else {
			throw new RuntimeException("Não foi possível atualizar o registro não existe ou foi apagado");
		}
	}

	public boolean delete(Long id){
		Optional<Customer> op = repository.findById(id);
		if(op.isPresent()){
			repository.deleteById(id);
			return true;
		}
		return false;
	}


}
