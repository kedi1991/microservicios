package com.example.springboot.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.multiplication.service.MultiplicationService;

@RestController
public class MultiplicationController {

	private final MultiplicationService multiplicationService;
	
	@Autowired
	public MultiplicationController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}
	
}
