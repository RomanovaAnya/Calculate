package ru.neoflex.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.practice.entity.CalculationResult;

@Repository
public interface CalculationResultRepository extends JpaRepository<CalculationResult,Long> { }
