package com.city.service.impl;

import com.city.exception.InvalidInputException;
import com.city.model.Complaint;
import com.city.model.enums.*;
import com.city.repository.ComplaintRepository;
import com.city.service.ComplaintService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository repo;
    private final AtomicLong seq = new AtomicLong(1000);

    public ComplaintServiceImpl(ComplaintRepository repo) { this.repo = repo; }

    @Override
    public Complaint registerComplaint(String citizenId, ComplaintCategory category, String description, PriorityLevel priority) {
        if (citizenId == null || citizenId.isBlank()) throw new InvalidInputException("citizenId required");
        if (category == null) throw new InvalidInputException("category required");
        String id = "C" + seq.incrementAndGet();
        Complaint c = new Complaint(id, citizenId, category, description, priority);
        repo.save(c);
        return c;
    }

    @Override
    public void updateComplaintStatus(String complaintId, ComplaintStatus status) {
        var c = repo.findById(complaintId).orElseThrow(() -> new InvalidInputException("Complaint not found"));
        c.setStatus(status);
        repo.update(c);
    }

    @Override
    public List<Complaint> getComplaintsByPriority(PriorityLevel priority) {
        return repo.findByPriority(priority);
    }
}