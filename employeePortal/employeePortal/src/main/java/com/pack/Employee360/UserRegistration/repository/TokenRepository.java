package com.pack.Employee360.UserRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.Employee360.UserRegistration.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long>{

	Token findByToken(String token);

}
