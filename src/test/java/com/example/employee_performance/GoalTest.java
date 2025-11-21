package com.example.employee_performance;

import com.example.employee_performance.model.Goal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    public void testGoalCreation() {
        // Arrange
        Goal goal = new Goal();
        goal.setDescription("Complete Spring Project");
        goal.setTargetDate(LocalDate.of(2025, 12, 31));
        goal.setCompleted(false);

        // Act & Assert
        assertEquals("Complete Spring Project", goal.getDescription());
        assertEquals(LocalDate.of(2025, 12, 31), goal.getTargetDate());
        assertFalse(goal.isCompleted());
    }
}
