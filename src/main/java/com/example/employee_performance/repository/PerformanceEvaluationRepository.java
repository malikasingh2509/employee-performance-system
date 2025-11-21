package com.example.employee_performance.repository;

import com.example.employee_performance.model.Employee;
import com.example.employee_performance.model.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceEvaluationRepository
        extends JpaRepository<PerformanceEvaluation, Long> {

    List<PerformanceEvaluation> findByEmployee(Employee employee);
}
