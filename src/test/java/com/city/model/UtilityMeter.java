package com.city.model;
import com.city.model.enums.UtilityType;
import java.time.LocalDateTime;
public class UtilityMeter {
    private final String meterId;
    private final String propertyId;
    private final UtilityType utilityType;
    private double currentReading;
    private LocalDateTime lastUpdated;
    public UtilityMeter(String meterId, String propertyId, UtilityType utilityType) {
        if (meterId == null || meterId.isBlank()) throw new IllegalArgumentException("meterId required");
        if (propertyId == null || propertyId.isBlank()) throw new IllegalArgumentException("propertyId required");
        if (utilityType == null) throw new IllegalArgumentException("utilityType required");
        this.meterId = meterId;
        this.propertyId = propertyId;
        this.utilityType = utilityType;
    }
    public String getMeterId() { return meterId; }
    public String getPropertyId() { return propertyId; }
    public UtilityType getUtilityType() { return utilityType; }
    public double getCurrentReading() { return currentReading; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setCurrentReading(double reading) {
        this.currentReading = reading;
        this.lastUpdated = LocalDateTime.now();
    }
}