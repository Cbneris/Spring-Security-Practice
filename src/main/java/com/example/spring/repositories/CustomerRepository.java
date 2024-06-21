package com.example.spring.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.spring.entity.CustomerEntity;


public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
	
	Optional<CustomerEntity> findByEmail(String email);
	
}
