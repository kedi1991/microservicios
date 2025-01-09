package com.example.springboot.multiplication.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;
import com.example.springboot.multiplication.service.MultiplicationService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {
	
	private final MultiplicationService multiplicationService;
	
	@Autowired
	public MultiplicationResultAttemptController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}
	
	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	@Getter
	public static final class ResultResponse{
		private final boolean correct;
	}
	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt){
		
		boolean isCorrect = multiplicationService.checkAttempt(attempt);
		
		MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);
		
		return ResponseEntity.ok(attemptCopy);
	}
}
