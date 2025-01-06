package com.example.springboot.mutliplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationService;
import com.example.springboot.multiplication.service.RandomGeneratorService;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

	private RandomGeneratorService randomGeneratorService;
	
	@Autowired
	public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
		super();
		this.randomGeneratorService = randomGeneratorService;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int argA = randomGeneratorService.generateRandomNumber();
		int argB = randomGeneratorService.generateRandomNumber();
		
		return new Multiplication(argA, argB);
	}
}
