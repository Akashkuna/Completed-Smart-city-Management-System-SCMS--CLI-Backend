package com.city.model;
import com.city.model.enums.SensorStatus;
import java.time.LocalDateTime;
public class TrafficSensor {
    private final String sensorId;
    private final String location;
    private int currentTrafficDensity;
    private LocalDateTime timestamp;
    private SensorStatus status = SensorStatus.ACTIVE;
    public TrafficSensor(String sensorId, String location) {
        if (sensorId == null || sensorId.isBlank()) throw new IllegalArgumentException("sensorId required");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("location required");
        this.sensorId = sensorId;
        this.location = location;
    }
    public String getSensorId() { return sensorId; }
    public String getLocation() { return location; }
    public int getCurrentTrafficDensity() { return currentTrafficDensity; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public SensorStatus getStatus() { return status; }
    public void setCurrentTrafficDensity(int density) {
        this.currentTrafficDensity = density;
        this.timestamp = LocalDateTime.now();
    }
    public void setStatus(SensorStatus status) { this.status = status; }
}