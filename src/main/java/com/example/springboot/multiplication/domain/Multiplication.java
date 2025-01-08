package com.example.springboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The multiplication class
 */
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Multiplication {
	
	private int argA;
	private int argB;
	
	//the result of the calculation
	private int result;

	public Multiplication() {
		//this inits the args of the all args constructor
		this(0,0);
	}
}
