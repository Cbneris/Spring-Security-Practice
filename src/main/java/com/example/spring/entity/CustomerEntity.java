package com.example.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tt_customers")
@Data
public class CustomerEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5694204883326298703L;
	
	@Id
	private Long id_customers;
	
	@Column
	private String email;
	
	@Column(name = "pwd")
	private String password;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "customers")
	private List<RolEntity> roles;
	
}
