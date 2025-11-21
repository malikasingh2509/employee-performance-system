package com.example.employee_performance.repository;

import com.example.employee_performance.model.Feedback;
import com.example.employee_performance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByEmployee(Employee employee);
}

