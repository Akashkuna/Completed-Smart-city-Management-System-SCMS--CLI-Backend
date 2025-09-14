package com.city.repository.memory;
import com.city.model.EmergencyAlert;
import com.city.repository.EmergencyRepository;
import java.util.*;
public class InMemoryEmergencyRepository implements EmergencyRepository {
    private final PriorityQueue<EmergencyAlert> pq =
            new PriorityQueue<>(Comparator.comparingInt((EmergencyAlert a) -> a.getSeverity().ordinal()).reversed());
    @Override
    public void add(EmergencyAlert alert) {
        pq.add(alert);
    }
    @Override
    public Optional<EmergencyAlert> peek() {
        return Optional.ofNullable(pq.peek());
    }
    @Override
    public Optional<EmergencyAlert> poll() {
        return Optional.ofNullable(pq.poll());
    }
    @Override
    public List<EmergencyAlert> active() {
        return new ArrayList<>(pq);
    }
}