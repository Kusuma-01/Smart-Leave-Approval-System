package com.project2.Leave_Approval.controller;

import com.project2.Leave_Approval.entity.LeaveRequest;
import com.project2.Leave_Approval.service.LeaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveController {

    private final LeaveService service;

    public LeaveController(LeaveService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String viewLeaves(Model model) {
        model.addAttribute("leaves", service.getAllLeaves());
        return "leave_list";
    }

    @GetMapping("/apply")
    public String applyLeave(Model model) {
        model.addAttribute("leave", new LeaveRequest());
        return "apply_leave";
    }

    @PostMapping("/save")
    public String saveLeave(@ModelAttribute("leave") LeaveRequest leave) {
        service.saveLeave(leave);
        return "redirect:/";
    }

    @GetMapping("/approve/{id}")
    public String approveLeave(@PathVariable Long id) {
        service.updateStatus(id, "APPROVED");
        return "redirect:/";
    }

    @GetMapping("/reject/{id}")
    public String rejectLeave(@PathVariable Long id) {
        service.updateStatus(id, "REJECTED");
        return "redirect:/";
    }
}
