package com.example.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PracticeControllerTest {

	@LocalServerPort
	private int port;
	
	private URI base;
	
	@Autowired
	private TestRestTemplate template;
	
	@BeforeEach
	private void setUp() throws URISyntaxException {
		this.base = new URI("http://localhost:" + port + "/");
	}
	
	@Test
	public void CheckTest() {
		ResponseEntity<String> response = template.getForEntity(base, String.class);
		assertThat(response.getBody()).isEqualTo("Greetings from Kedi!");
	}
}
