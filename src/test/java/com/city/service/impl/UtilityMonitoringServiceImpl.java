package com.city.service.impl;

import com.city.exception.InvalidInputException;
import com.city.model.enums.UtilityType;
import com.city.service.UtilityMonitoringService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UtilityMonitoringServiceImpl implements UtilityMonitoringService {
    private final Map<String, Double> readings = new ConcurrentHashMap<>(); // key: meterId

    @Override
    public void recordUtilityConsumption(String meterId, double reading) {
        if (meterId == null || meterId.isBlank() || reading < 0)
            throw new InvalidInputException("Invalid meter or reading");
        readings.put(meterId, reading);
    }

    @Override
    public double generateBill(String propertyId, UtilityType type) {
        // simple stub rates to enable tests/demo
        return switch (type) {
            case WATER -> 100.0;
            case ELECTRICITY -> 250.0;
            case GAS -> 150.0;
        };
    }
}