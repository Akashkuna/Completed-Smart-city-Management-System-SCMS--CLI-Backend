package com.city.service;

import com.city.model.Complaint;
import com.city.model.enums.*;
import java.util.List;

public interface ComplaintService {
    Complaint registerComplaint(String citizenId, ComplaintCategory category, String description, PriorityLevel priority);
    void updateComplaintStatus(String complaintId, ComplaintStatus status);
    List<Complaint> getComplaintsByPriority(PriorityLevel priority);
}