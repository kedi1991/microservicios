package com.example.springboot.multiplication.domain;

import lombok.AllArgsConstructor;
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
	
	private final int argA;
	private final int argB;
	

	public Multiplication() {
		//this inits the args of the all args constructor
		this(0,0);
	}
}
