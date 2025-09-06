package com.city.repository;

import com.city.model.TrafficSensor;
import java.util.*;

public interface TrafficRepository {
    Optional<TrafficSensor> findById(String id);
    void save(TrafficSensor sensor);
    Collection<TrafficSensor> findAll();
}