package com.city.repository.memory;

import com.city.model.Complaint;
import com.city.model.enums.PriorityLevel;
import com.city.repository.ComplaintRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryComplaintRepository implements ComplaintRepository {
    private final Map<String, Complaint> store = new ConcurrentHashMap<>();
    public Optional<Complaint> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public void save(Complaint c) { store.put(c.getComplaintId(), c); }
    public void update(Complaint c) { store.put(c.getComplaintId(), c); }
    public List<Complaint> findByPriority(PriorityLevel p) {
        return store.values().stream()
                .filter(c -> c.getPriority() == p)
                .sorted(Comparator.comparing(Complaint::getCreatedAt))
                .collect(Collectors.toList());
    }
}