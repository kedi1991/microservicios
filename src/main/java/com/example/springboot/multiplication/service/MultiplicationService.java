package com.example.springboot.multiplication.service;

import java.util.List;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
/**\
 * Create a multiplication object with 2 randomly generated number between 0 and 100
 * 
 * @return a {@link Multiplication} object with 2 random numbers
 */
	
	Multiplication createRandomMultiplication();
	
	/*
	 * Returns true of the result is correct, false otherwise
	 * */
	boolean checkAttempt(MultiplicationResultAttempt resultAttempt);
	
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

}
