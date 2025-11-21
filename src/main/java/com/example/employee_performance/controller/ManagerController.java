package com.example.employee_performance.controller;

import com.example.employee_performance.model.*;
import com.example.employee_performance.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final EmployeeRepository employeeRepository;
    private final PerformanceEvaluationRepository evaluationRepository;
    private final GoalRepository goalRepository;
    private final FeedbackRepository feedbackRepository;

    public ManagerController(EmployeeRepository employeeRepository,
                             PerformanceEvaluationRepository evaluationRepository,
                             GoalRepository goalRepository,
                             FeedbackRepository feedbackRepository) {
        this.employeeRepository = employeeRepository;
        this.evaluationRepository = evaluationRepository;
        this.goalRepository = goalRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Employee manager = (Employee) session.getAttribute("user");

        if (manager == null || !"MANAGER".equalsIgnoreCase(manager.getRole())) {
            return "redirect:/";
        }

        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "manager-dashboard";
    }

    @PostMapping("/evaluate")
    public String evaluate(@RequestParam Long employeeId,
                           @RequestParam String criteria,
                           @RequestParam int rating,
                           @RequestParam String comments,
                           HttpSession session) {

        Employee manager = (Employee) session.getAttribute("user");
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (manager == null || employee == null) return "redirect:/";

        PerformanceEvaluation eval = new PerformanceEvaluation();
        eval.setCriteria(criteria);
        eval.setRating(rating);
        eval.setComments(comments);
        eval.setEvaluationDate(LocalDate.now());
        eval.setManager(manager);
        eval.setEmployee(employee);

        evaluationRepository.save(eval);

        return "redirect:/manager/dashboard";
    }

    @PostMapping("/set-goal")
    public String setGoal(@RequestParam Long employeeId,
                          @RequestParam String description,
                          @RequestParam String targetDate) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) return "redirect:/manager/dashboard";

        Goal goal = new Goal();
        goal.setDescription(description);
        goal.setTargetDate(LocalDate.parse(targetDate));
        goal.setCompleted(false);
        goal.setEmployee(employee);

        goalRepository.save(goal);

        return "redirect:/manager/dashboard";
    }

    @PostMapping("/feedback")
    public String giveFeedback(@RequestParam Long employeeId,
                               @RequestParam String comments,
                               @RequestParam int rating,
                               HttpSession session) {

        Employee manager = (Employee) session.getAttribute("user");
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (manager == null || employee == null) return "redirect:/";

        Feedback feedback = new Feedback();
        feedback.setComments(comments);
        feedback.setRating(rating);
        feedback.setFeedbackDate(LocalDate.now());
        feedback.setEmployee(employee);
        feedback.setManager(manager);

        feedbackRepository.save(feedback);

        return "redirect:/manager/dashboard";
    }
}
