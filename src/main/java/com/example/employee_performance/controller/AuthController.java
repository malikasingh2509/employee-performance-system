package com.example.employee_performance.controller;

import com.example.employee_performance.model.Employee;
import com.example.employee_performance.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Employee user = employeeService.login(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        session.setAttribute("user", user);

        if ("MANAGER".equalsIgnoreCase(user.getRole())) {
            return "redirect:/manager/dashboard";
        } else {
            return "redirect:/employee/dashboard";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

