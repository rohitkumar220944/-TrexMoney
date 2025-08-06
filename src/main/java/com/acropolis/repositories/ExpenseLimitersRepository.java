package com.acropolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.ExpenseLimiters;

@Repository
public interface ExpenseLimitersRepository extends JpaRepository<ExpenseLimiters, Integer> {

}
