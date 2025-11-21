package com.example.employee_performance.controller;

import com.example.employee_performance.model.Employee;
import com.example.employee_performance.model.Goal;
import com.example.employee_performance.model.PerformanceEvaluation;
import com.example.employee_performance.model.Feedback;
import com.example.employee_performance.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final PerformanceEvaluationRepository evaluationRepository;
    private final FeedbackRepository feedbackRepository;
    private final GoalRepository goalRepository;

    public EmployeeController(PerformanceEvaluationRepository evaluationRepository,
                              FeedbackRepository feedbackRepository,
                              GoalRepository goalRepository) {
        this.evaluationRepository = evaluationRepository;
        this.feedbackRepository = feedbackRepository;
        this.goalRepository = goalRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("user");

        if (employee == null) {
            return "redirect:/";
        }

        List<PerformanceEvaluation> evaluations = evaluationRepository.findByEmployee(employee);
        List<Feedback> feedbackList = feedbackRepository.findByEmployee(employee);
        List<Goal> goals = goalRepository.findByEmployee(employee);

        model.addAttribute("evaluations", evaluations);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("goals", goals);
        model.addAttribute("employee", employee);   // ***** MISSING LINE FIXED ******

        return "employee-dashboard";
    }


    @PostMapping("/add-goal")
    public String addGoal(@RequestParam String description,
                          @RequestParam String targetDate,
                          HttpSession session) {

        Employee employee = (Employee) session.getAttribute("user");

        if (employee == null) return "redirect:/";

        Goal goal = new Goal();
        goal.setDescription(description);
        goal.setTargetDate(LocalDate.parse(targetDate));
        goal.setCompleted(false);
        goal.setEmployee(employee);

        goalRepository.save(goal);

        return "redirect:/employee/dashboard";
    }
}


