package com.city.repository;
import com.city.model.EmergencyAlert;
import java.util.*;
public interface EmergencyRepository {
    void add(EmergencyAlert alert);
    Optional<EmergencyAlert> peek();
    Optional<EmergencyAlert> poll();
    List<EmergencyAlert> active();
}