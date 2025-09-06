package com.city.service;

import com.city.model.enums.UtilityType;

public interface UtilityMonitoringService {
    void recordUtilityConsumption(String meterId, double reading);
    double generateBill(String propertyId, UtilityType type);
}