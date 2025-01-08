package com.example.springboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class User {
	
	private final String alias;
	
	protected User() {
		this.alias = null;
	}

}
