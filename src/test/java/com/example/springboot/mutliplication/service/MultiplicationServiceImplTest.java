package com.example.springboot.mutliplication.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;
import com.example.springboot.multiplication.domain.User;
import com.example.springboot.multiplication.repository.MultiplicationResultAttemptRepository;
import com.example.springboot.multiplication.repository.UserRepository;
import com.example.springboot.multiplication.service.MultiplicationServiceImpl;
import com.example.springboot.multiplication.service.RandomGeneratorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceImplTest {

	
	private MultiplicationServiceImpl multiplicationServiceImpl;
	
	@MockBean
	private RandomGeneratorService randomGeneratorService;
	
	@Mock //this keeps the test focused on the service layer
	private UserRepository userRepository;
	
	@Mock
	private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
	
	@Before
	public void setup() {
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, multiplicationResultAttemptRepository, userRepository);
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
	
	@Test
	public void checkCorrectAttemptTest() {
		
		//given
		Multiplication multiplication = new Multiplication(5,6);
		User user = new User("okurut");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 30, false);
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 30, true);

		given(userRepository.findByAlias("okurut")).willReturn(Optional.empty());
		
		//when
		boolean attemptresult = multiplicationServiceImpl.checkAttempt(attempt);
		//assert
		assertThat(attemptresult).isTrue();
		verify(multiplicationResultAttemptRepository).save(verifiedAttempt);
	}
	
	@Test
	public void checkIncorrectAttemptTest() {
		
		//given
		Multiplication multiplication = new Multiplication(5,6);
		User user = new User("okurut");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 31, false);
		
		given(userRepository.findByAlias("okurut")).willReturn(Optional.empty());
		
		//when
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);		
		//assert
		assertThat(attemptResult).isFalse();	
		verify(multiplicationResultAttemptRepository).save(attempt);

	}
}
