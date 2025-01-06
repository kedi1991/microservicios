package com.example.springboot.mutliplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationService;
import com.example.springboot.multiplication.service.RandomGeneratorService;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class MultiplicationServiceTest {
	
	@Autowired
	MultiplicationService multiplicationService;
		
	@MockBean
	RandomGeneratorService randomGeneratorService;
	
	@Test
	public void getRandomNumberTest(){
		
		//given that
		given(randomGeneratorService.generateRandomNumber()).willReturn(10, 20);
		//when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		//then
		assertThat(multiplication.getArgA()).isEqualTo(10);
		assertThat(multiplication.getArgB()).isEqualTo(20);
		assertThat(multiplication.getResult()).isEqualTo(200);

	}
	
}
