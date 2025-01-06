package com.example.springboot.multiplication.service;

import com.example.springboot.multiplication.domain.Multiplication;

public interface MultiplicationService {
/**\
 * Create a multiplication object with 2 randomly generated number between 0 and 100
 * 
 * @return a multiplication object with 2 random numbers
 */
	
	Multiplication createRandomMultiplication();
}
