package com.example.api.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.api.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findAllByOrderByNameAsc(Pageable pageable);
}
