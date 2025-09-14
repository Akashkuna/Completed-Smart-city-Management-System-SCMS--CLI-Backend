package com.city.repository.memory;
import com.city.model.Citizen;
import com.city.repository.CitizenRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class InMemoryCitizenRepository implements CitizenRepository {
    private final Map<String, Citizen> store = new ConcurrentHashMap<>();
    public Optional<Citizen> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public void save(Citizen c) { store.put(c.getCitizenId(), c); }
    public Collection<Citizen> findAll() { return store.values(); }
}