package com.example.springboot.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationService;

@RestController
@RequestMapping("multiplications")
public class MultiplicationController {

	private final MultiplicationService multiplicationService;
	
	@Autowired
	public MultiplicationController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}
	
	@GetMapping("/random")
	private Multiplication getRandomMultiplication() {
		return multiplicationService.createRandomMultiplication();
	}
	
}
