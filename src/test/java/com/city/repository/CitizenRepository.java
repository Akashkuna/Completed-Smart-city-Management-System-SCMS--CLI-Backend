package com.city.repository;
import com.city.model.Citizen;
import java.util.*;
public interface CitizenRepository {
    Optional<Citizen> findById(String id);
    void save(Citizen citizen);
    Collection<Citizen> findAll();
}