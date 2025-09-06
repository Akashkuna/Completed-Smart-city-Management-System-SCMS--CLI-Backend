package com.city.model;

import com.city.model.enums.AlertStatus;
import com.city.model.enums.EmergencyType;
import com.city.model.enums.PriorityLevel;
import java.time.LocalDateTime;

public class EmergencyAlert {
    private final String alertId;
    private final EmergencyType type;
    private final String location;
    private final PriorityLevel severity;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private AlertStatus status = AlertStatus.NEW;

    public EmergencyAlert(String alertId, EmergencyType type, String location, PriorityLevel severity) {
        if (alertId == null || alertId.isBlank()) throw new IllegalArgumentException("alertId required");
        if (type == null) throw new IllegalArgumentException("type required");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("location required");
        if (severity == null) throw new IllegalArgumentException("severity required");
        this.alertId = alertId;
        this.type = type;
        this.location = location;
        this.severity = severity;
    }

    public String getAlertId() { return alertId; }
    public EmergencyType getType() { return type; }
    public String getLocation() { return location; }
    public PriorityLevel getSeverity() { return severity; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public AlertStatus getStatus() { return status; }
    public void setStatus(AlertStatus status) { this.status = status; }
}