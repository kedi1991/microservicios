package com.example.springboot.multiplication.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService{

	final static int MINFACTOR = 0;
	final static int MAXFACTOR = 10;
	
	@Override
	public int generateRandomNumber() {
		// TODO Auto-generated method stub
		
		return new Random().nextInt(MAXFACTOR - MINFACTOR + 1) + MINFACTOR;
	}

}
