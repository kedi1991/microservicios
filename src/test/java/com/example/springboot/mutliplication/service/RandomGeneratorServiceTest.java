package com.example.springboot.mutliplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.multiplication.service.RandomGeneratorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomGeneratorServiceTest {

	@Autowired
	private RandomGeneratorService randomGeneratorService;
	
	@Test
	public void generateRandomFactorIsBetweenExpectedLimits() {
		List<Integer> randomFactors = IntStream.range(0, 10)
		.map(i -> randomGeneratorService.generateRandomNumber())
		.boxed()
		.collect(Collectors.toList());
		
		assertThat(randomFactors).containsAnyElementsOf(IntStream.range(0,10).boxed().collect(Collectors.toList()));
	}
}
