package com.example.springboot.mutliplication.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationServiceImpl;
import com.example.springboot.multiplication.service.RandomGeneratorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceImplTest {

	
	private MultiplicationServiceImpl multiplicationServiceImpl;
	
	@MockBean
	private RandomGeneratorService randomGeneratorService;
	
	@Before
	public void setup() {
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
	}
	
	@Test
	public void createRandomMultiplicationTest() {
		//given
		given(randomGeneratorService.generateRandomNumber()).willReturn(3, 5);
		
		//when
		Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
		//assert
		assertThat(multiplication.getArgA()).isEqualTo(3);
	}
}
