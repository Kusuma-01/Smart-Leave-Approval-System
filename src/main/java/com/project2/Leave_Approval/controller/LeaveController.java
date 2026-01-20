package com.project2.Leave_Approval.controller;

import com.project2.Leave_Approval.entity.LeaveRequest;
import com.project2.Leave_Approval.service.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService service;

    // HOME PAGE
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // STUDENT FORM PAGE
    @GetMapping("/student")
    public String studentForm(Model model) {
        model.addAttribute("leave", new LeaveRequest());
        return "student_form";
    }

    // SAVE LEAVE (SUBMIT)
    @PostMapping("/save")
    public String saveLeave(@ModelAttribute LeaveRequest leave) {
        service.saveLeave(leave);
        return "redirect:/";   // ✅ redirects to home
    }

    // ADMIN LOGIN PAGE
    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    // ADMIN AUTH
    @PostMapping("/adminLogin")
    public String adminAuth(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        if ("hod".equals(username) && "hod123".equals(password)) {
            session.setAttribute("role", "ADMIN");
            return "redirect:/leaves";   // ✅ REDIRECT HERE
        }

        model.addAttribute("error", "Invalid credentials");
        return "admin_login";
    }

    // VIEW LEAVES
    @GetMapping("/leaves")
    public String viewLeaves(Model model, HttpSession session) {
        model.addAttribute("leaves", service.getAllLeaves());
        model.addAttribute("role", session.getAttribute("role"));
        return "leave_list";
    }


    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id, HttpSession session) {
        if ("ADMIN".equals(session.getAttribute("role"))) {
            service.approve(id);
        }
        return "redirect:/leaves";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id, HttpSession session) {
        if ("ADMIN".equals(session.getAttribute("role"))) {
            service.reject(id);
        }
        return "redirect:/leaves";
    }
}
