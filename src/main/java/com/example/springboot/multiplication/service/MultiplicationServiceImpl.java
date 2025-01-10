package com.example.springboot.multiplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;
import com.example.springboot.multiplication.domain.User;
import com.example.springboot.multiplication.repository.MultiplicationResultAttemptRepository;
import com.example.springboot.multiplication.repository.UserRepository;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;
	private RandomGeneratorService randomGeneratorService;
	
	@Autowired
	public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService, final MultiplicationResultAttemptRepository attemptRepository, final UserRepository userRepository) {
		super();
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int argA = randomGeneratorService.generateRandomNumber();
		int argB = randomGeneratorService.generateRandomNumber();
		
		return new Multiplication(argA, argB);
	}

	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
		
		//check if user already exists 
		Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
		
		//chek if the attempt is correct
		boolean isCorrect = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getArgA()* resultAttempt.getMultiplication().getArgB();
		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(resultAttempt.getUser()), resultAttempt.getMultiplication(), resultAttempt.getResultAttempt(), isCorrect);
		
		//store the attempt
		attemptRepository.save(checkedAttempt);
		
		return resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getArgA() * resultAttempt.getMultiplication().getArgB();
	}
}
