package com.example.springboot.multiplication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.multiplication.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByAlias(final String alias);
}
