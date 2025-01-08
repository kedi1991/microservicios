package com.example.springboot.mutliplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.example.springboot.multiplication.service.RandomGeneratorService;
import com.example.springboot.multiplication.service.RandomGeneratorServiceImpl;

public class RandomGeneratorServiceImplTest {

	private RandomGeneratorService randomGeneratorServiceImpl;
	
	@Before
	public void setUp() {
		randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
			
		}
	
	@Test
	public void generateRandomFactorIsBetweenExpectedLimits() {
		//given
		
		//when
		List<Integer> randomFactors = IntStream.range(0, 10)
				.map(i -> randomGeneratorServiceImpl.generateRandomNumber())
				.boxed()
				.collect(Collectors.toList());
		//then
		assertThat(randomFactors).containsAnyElementsOf(IntStream.range(0, 10).boxed().collect(Collectors.toList()));
	}
	
	}
