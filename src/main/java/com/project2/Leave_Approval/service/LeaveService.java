package com.project2.Leave_Approval.service;

import com.project2.Leave_Approval.entity.LeaveRequest;
import com.project2.Leave_Approval.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository repo;

    public LeaveService(LeaveRepository repo) {
        this.repo = repo;
    }

    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }

    public void saveLeave(LeaveRequest leave) {
        leave.setStatus("PENDING");
        repo.save(leave);
    }

    public void updateStatus(Long id, String status) {
        LeaveRequest leave = repo.findById(id).orElseThrow();
        leave.setStatus(status);
        repo.save(leave);
    }
}
