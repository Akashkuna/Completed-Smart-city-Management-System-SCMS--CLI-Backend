package com.city.service;
import com.city.model.TrafficSensor;
import java.util.List;
public interface TrafficManagementService {
    void updateTrafficDensity(String sensorId, int density);
    List<TrafficSensor> getHighTrafficAreas(int threshold);
}