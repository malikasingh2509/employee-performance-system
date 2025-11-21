package com.example.employee_performance.repository;

import com.example.employee_performance.model.Goal;
import com.example.employee_performance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByEmployee(Employee employee);
}
