package com.example.springboot.multiplication.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
public class MultiplicationResultAttempt {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private final User user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")	
	private final Multiplication multiplication;
	private final int resultAttempt;
	private final boolean correct;
	
	//empty constructor for JSON de-serilization
	public MultiplicationResultAttempt() {
		this.user = null;
		this.multiplication = null;
		this.resultAttempt = -1;
		correct = false;
	}
}
