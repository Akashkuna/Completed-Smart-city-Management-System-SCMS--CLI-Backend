package com.city.model;

import com.city.model.enums.ServiceType;

public class PublicService {
    private final String serviceId;
    private final ServiceType serviceType;
    private final String location;
    private boolean available = true;

    public PublicService(String serviceId, ServiceType serviceType, String location) {
        if (serviceId == null || serviceId.isBlank()) throw new IllegalArgumentException("serviceId required");
        if (serviceType == null) throw new IllegalArgumentException("serviceType required");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("location required");
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.location = location;
    }

    public String getServiceId() { return serviceId; }
    public ServiceType getServiceType() { return serviceType; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}