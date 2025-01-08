package com.example.springboot.mutliplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationService;
import com.example.springboot.multiplication.service.RandomGeneratorService;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {
	
	@Autowired
	MultiplicationService multiplicationService;
		
	@MockBean
	RandomGeneratorService randomGeneratorService;
	
	@Test
	public void getRandomNumberTest(){
		
		//given that
		given(randomGeneratorService.generateRandomNumber()).willReturn(5, 4);
		//when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		//then
		assertThat(multiplication.getArgA()).isEqualTo(5);
		assertThat(multiplication.getArgB()).isEqualTo(4);
		//assertThat(multiplication.getResult()).isEqualTo(20);

	}
	
}
