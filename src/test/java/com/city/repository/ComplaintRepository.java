package com.city.repository;
import com.city.model.Complaint;
import com.city.model.enums.PriorityLevel;
import java.util.*;
public interface ComplaintRepository {
    Optional<Complaint> findById(String id);
    void save(Complaint complaint);
    void update(Complaint complaint);
    List<Complaint> findByPriority(PriorityLevel priority);
}