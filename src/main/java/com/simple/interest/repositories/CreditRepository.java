package com.simple.interest.repositories;

import com.simple.interest.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}


