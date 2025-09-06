package com.city.service.impl;

import com.city.exception.EmergencyPriorityException;
import com.city.exception.InvalidInputException;
import com.city.model.EmergencyAlert;
import com.city.model.enums.AlertStatus;
import com.city.model.enums.EmergencyType;
import com.city.model.enums.PriorityLevel;
import com.city.repository.EmergencyRepository;
import com.city.service.EmergencyService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyServiceImpl implements EmergencyService {
    private final EmergencyRepository repo;
    private final Map<String, EmergencyAlert> index = new ConcurrentHashMap<>();
    private long seq = 5000;

    public EmergencyServiceImpl(EmergencyRepository repo) { this.repo = repo; }

    @Override
    public synchronized EmergencyAlert raiseEmergencyAlert(EmergencyType type, String location, PriorityLevel severity) {
        if (type == null || location == null || location.isBlank() || severity == null)
            throw new InvalidInputException("type, location, severity required");
        String id = "E" + (++seq);
        EmergencyAlert alert = new EmergencyAlert(id, type, location, severity);
        repo.add(alert);
        index.put(id, alert);
        if (severity == PriorityLevel.LOW)
            throw new EmergencyPriorityException("Low severity queued; escalation may be required");
        return alert;
    }

    @Override
    public synchronized void dispatchEmergencyService(String alertId) {
        EmergencyAlert a = index.get(alertId);
        if (a == null) throw new InvalidInputException("alert not found");
        a.setStatus(AlertStatus.DISPATCHED);
    }

    @Override
    public synchronized List<EmergencyAlert> getActiveEmergencies() {
        return repo.active();
    }
}