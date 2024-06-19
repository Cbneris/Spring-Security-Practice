package com.example.spring.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {
	
	@GetMapping()
	public Map<String, String> accounts() {
		return Collections.singletonMap("msg", "accounts");
	}
	
}