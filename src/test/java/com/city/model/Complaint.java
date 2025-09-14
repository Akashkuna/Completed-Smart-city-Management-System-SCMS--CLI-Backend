package com.city.model;
import com.city.model.enums.ComplaintCategory;
import com.city.model.enums.ComplaintStatus;
import com.city.model.enums.PriorityLevel;
import java.time.LocalDateTime;
public class Complaint {
    private final String complaintId;
    private final String citizenId;
    private final ComplaintCategory category;
    private final String description;
    private ComplaintStatus status = ComplaintStatus.OPEN;
    private final PriorityLevel priority;
    private final LocalDateTime createdAt = LocalDateTime.now();
    public Complaint(String complaintId, String citizenId,
                     ComplaintCategory category, String description, PriorityLevel priority) {
        if (complaintId == null || complaintId.isBlank()) throw new IllegalArgumentException("complaintId required");
        if (citizenId == null || citizenId.isBlank()) throw new IllegalArgumentException("citizenId required");
        if (category == null) throw new IllegalArgumentException("category required");
        this.complaintId = complaintId;
        this.citizenId = citizenId;
        this.category = category;
        this.description = description;
        this.priority = (priority == null) ? PriorityLevel.MEDIUM : priority;
    }
    public String getComplaintId() { return complaintId; }
    public String getCitizenId() { return citizenId; }
    public ComplaintCategory getCategory() { return category; }
    public String getDescription() { return description; }
    public ComplaintStatus getStatus() { return status; }
    public PriorityLevel getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setStatus(ComplaintStatus status) { this.status = status; }
}