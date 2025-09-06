package com.city.service.impl;

import com.city.exception.InvalidInputException;
import com.city.model.TrafficSensor;
import com.city.repository.TrafficRepository;
import com.city.service.TrafficManagementService;

import java.util.List;
import java.util.stream.Collectors;

public class TrafficManagementServiceImpl implements TrafficManagementService {
    private final TrafficRepository repo;

    public TrafficManagementServiceImpl(TrafficRepository repo) { this.repo = repo; }

    @Override
    public void updateTrafficDensity(String sensorId, int density) {
        if (density < 0) throw new InvalidInputException("Density cannot be negative");
        TrafficSensor s = repo.findById(sensorId)
                .orElseThrow(() -> new InvalidInputException("Sensor not found"));
        s.setCurrentTrafficDensity(density);
    }

    @Override
    public List<TrafficSensor> getHighTrafficAreas(int threshold) {
        return repo.findAll().stream()
                .filter(s -> s.getCurrentTrafficDensity() >= threshold)
                .collect(Collectors.toList());
    }
}