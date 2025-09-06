package com.city.service;

import com.city.model.EmergencyAlert;
import com.city.model.enums.*;
import java.util.List;

public interface EmergencyService {
    EmergencyAlert raiseEmergencyAlert(EmergencyType type, String location, PriorityLevel severity);
    void dispatchEmergencyService(String alertId);
    List<EmergencyAlert> getActiveEmergencies();
}