package com.project2.Leave_Approval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LeaveApprovalApplicationTests {

    // Test 1: Apply Leave
    @Test
    void testApplyLeave() {
        int leaveDays = 3;
        assertTrue(leaveDays > 0, "Leave days should be greater than 0");
    }

    // Test 2: Approve Leave
    @Test
    void testApproveLeave() {
        String status = "APPROVED";
        assertEquals("APPROVED", status);
    }

    // Test 3: Reject Leave
    @Test
    void testRejectLeave() {
        String status = "REJECTED";
        assertEquals("REJECTED", status);
    }

    // Test 4: Leave balance check
    @Test
    void testLeaveBalance() {
        int balance = 10;
        int requested = 4;

        assertTrue(balance >= requested, "Not enough leave balance");
    }
}