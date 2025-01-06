package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SBController {

	@GetMapping("sb")
	public String getSB() {

		return "SB reached";
	}
}
