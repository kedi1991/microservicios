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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.springboot.multiplication.domain.Multiplication;
import com.example.springboot.multiplication.service.MultiplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class) //init the context of the web app. this will laod only the controllers. the @SpringbpptTest will load all configs. It also loads MockMvc
public class MultiplicationControllerTest {
	
	@MockBean //dont ue the real impl with real bean, but we define it usin the give tag
	private MultiplicationService multiplicationService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<Multiplication> json;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void getRandomMultiplicationtest() throws Exception {
		
		//given : we are testing the controller and not the service.
		given(multiplicationService.createRandomMultiplication()).willReturn(new Multiplication(4, 5));
		
		//when
		MockHttpServletResponse response = mvc.perform(get("/multiplication/random")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();
				
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(new Multiplication(4,5)).getJson());
	}

}
