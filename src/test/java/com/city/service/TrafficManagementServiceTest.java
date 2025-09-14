package com.city.service;
import com.city.exception.InvalidInputException;
import com.city.model.TrafficSensor;
import com.city.repository.memory.InMemoryTrafficRepository;
import com.city.service.impl.TrafficManagementServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
public class TrafficManagementServiceTest {
    private InMemoryTrafficRepository repo;
    private TrafficManagementService service;
    @BeforeMethod
    public void setup() {
        repo = new InMemoryTrafficRepository();
        service = new TrafficManagementServiceImpl(repo);
        // seed a couple of sensors
        repo.save(new TrafficSensor("TS-100", "Main Junction"));
        repo.save(new TrafficSensor("TS-200", "North Bridge"));
        service.updateTrafficDensity("TS-100", 35);
        service.updateTrafficDensity("TS-200", 80);
    }
    @Test(groups = "traffic")
    public void getHighTrafficAreas_thresholdFiltersCorrectly() {
        List<TrafficSensor> high = service.getHighTrafficAreas(50);
        Assert.assertEquals(high.size(), 1);
        Assert.assertEquals(high.get(0).getSensorId(), "TS-200");
    }
    @Test(groups = "traffic")
    public void updateTrafficDensity_updatesReading() {
        service.updateTrafficDensity("TS-100", 55);
        List<TrafficSensor> high = service.getHighTrafficAreas(50);
        Assert.assertEquals(high.size(), 2, "Both sensors should meet threshold after update");
    }
    @Test(expectedExceptions = InvalidInputException.class, groups = "traffic", priority = 1)
    public void updateTrafficDensity_negative_throws() {
        service.updateTrafficDensity("TS-100", -1);
    }
    @Test(expectedExceptions = InvalidInputException.class, groups = "traffic", priority = 1)
    public void updateTrafficDensity_unknownSensor_throws() {
        service.updateTrafficDensity("TS-999", 10);
    }
}