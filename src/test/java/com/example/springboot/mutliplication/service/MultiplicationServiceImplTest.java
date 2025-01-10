package com.example.springboot.mutliplication.service;

import org.assertj.core.util.Lists;
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

import java.util.List;
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
	
	@Test
	public void retrieveStatsTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt2 = new	MultiplicationResultAttempt(user, multiplication, 3051, false);
	
		List<MultiplicationResultAttempt> latestAttempts =
		Lists.newArrayList(attempt1, attempt2);
	
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
		given(multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc("john_doe")).willReturn(latestAttempts);
	
		// when
		List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImpl.getStatsForUser("john_doe");

	}
}
