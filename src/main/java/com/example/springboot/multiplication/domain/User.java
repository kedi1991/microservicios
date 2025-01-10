package com.example.springboot.multiplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "User_multp")
public final class User {
	
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long Id;
	
	private final String alias;
	
	protected User() {
		this.alias = null;
	}

}
