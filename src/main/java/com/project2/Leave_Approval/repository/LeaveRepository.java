package com.project2.Leave_Approval.repository;

import com.project2.Leave_Approval.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
}
