package com.example.springboot.multiplication.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import com.example.springboot.multiplication.controller.MultiplicationResultAttemptController.ResultResponse;
import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;
import com.example.springboot.multiplication.domain.User;
import com.example.springboot.multiplication.service.MultiplicationService;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<MultiplicationResultAttempt> jsonResult;
	private JacksonTester<ResultResponse> jsonResponse;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void postResultResultCorrect() throws IOException, Exception {
		getParametizedtest(true);
	}
	
	@Test
	public void postResultResultNotCorrect() throws IOException, Exception {
			getParametizedtest(false);
	}
	
	void getParametizedtest(final boolean correct) throws IOException, Exception {
		//given :: we are note testing the service itself
		given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);
		
		User user = new User("kedi");
		Multiplication multiplication = new Multiplication(2, 5);
		
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 10, correct);
		
		//when
		MockHttpServletResponse response = mvc.perform(post("/results").accept(MediaType.APPLICATION_JSON)
				.content(jsonResult.write(attempt).getJson())
				).andReturn().getResponse();
		
		System.out.println(response);
		
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResult.write(new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), correct)).getJson());
		
	}
}
