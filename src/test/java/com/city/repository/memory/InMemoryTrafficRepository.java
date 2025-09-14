package com.city.repository.memory;
import com.city.model.TrafficSensor;
import com.city.repository.TrafficRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class InMemoryTrafficRepository implements TrafficRepository {
    private final Map<String, TrafficSensor> store = new ConcurrentHashMap<>();
    public Optional<TrafficSensor> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public void save(TrafficSensor s) { store.put(s.getSensorId(), s); }
    public Collection<TrafficSensor> findAll() { return store.values(); }
}