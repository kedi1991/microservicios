package com.example.springboot.multiplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long>{

	List<MultiplicationResultAttempt> findTop5ByUseraAliasOrderByIdDesc(String userAlias);
}
