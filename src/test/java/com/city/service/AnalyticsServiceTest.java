package com.city.service;

import com.city.service.impl.AnalyticsServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

public class AnalyticsServiceTest {

    private AnalyticsService service;

    @BeforeClass
    public void init() {
        service = new AnalyticsServiceImpl();
    }

    @Test(groups = {"analytics"})
    public void generateTrafficReport_ok() {
        Assert.assertEquals(service.generateTrafficReport(), "TRAFFIC_REPORT:OK");
    }

    @Test(groups = {"analytics"})
    public void generateUtilityConsumptionReport_ok() {
        Assert.assertEquals(service.generateUtilityConsumptionReport(), "UTILITY_REPORT:OK");
    }

    @Test(groups = {"analytics"})
    public void getComplaintStatistics_ok() {
        Assert.assertEquals(service.getComplaintStatistics(), "COMPLAINT_STATS:OK");
    }
}