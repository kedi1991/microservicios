package com.example.springboot.multiplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.multiplication.domain.Multiplication;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long>{

}
