package com.example.springboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class MultiplicationResultAttempt {

	private final User user;
	private final Multiplication multiplication;
	private final int resultAttempt;
	
	//empty constructor for JSON de-serilization
	public MultiplicationResultAttempt() {
		this.user = null;
		this.multiplication = null;
		this.resultAttempt = -1;
	}
}
