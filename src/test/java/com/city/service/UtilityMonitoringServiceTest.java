package com.city.service;

import com.city.exception.InvalidInputException;
import com.city.model.enums.UtilityType;
import com.city.service.impl.UtilityMonitoringServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

public class UtilityMonitoringServiceTest {

    private UtilityMonitoringService service;

    @BeforeClass
    public void init() {
        service = new UtilityMonitoringServiceImpl();
    }

    @DataProvider
    public Object[][] validReadings() {
        return new Object[][] {
                {"M-1", 0.0},
                {"M-2", 12.5},
                {"M-3", 99999.99}
        };
    }

    @Test(dataProvider = "validReadings", groups = "utility")
    public void recordUtilityConsumption_acceptsValid(String meterId, double reading) {
        service.recordUtilityConsumption(meterId, reading);
        // no exception == pass
        Assert.assertTrue(true);
    }

    @DataProvider
    public Object[][] invalidReadings() {
        return new Object[][] {
                {null, 10.0},
                {"", 10.0},
                {"   ", 10.0},
                {"M-4", -0.01}
        };
    }

    @Test(dataProvider = "invalidReadings", expectedExceptions = InvalidInputException.class, groups = "utility")
    public void recordUtilityConsumption_invalid_throws(String meterId, double reading) {
        service.recordUtilityConsumption(meterId, reading);
    }

    @DataProvider
    public Object[][] billCases() {
        return new Object[][] {
                {"P-1", UtilityType.WATER, 100.0},
                {"P-2", UtilityType.ELECTRICITY, 250.0},
                {"P-3", UtilityType.GAS, 150.0}
        };
    }

    @Test(dataProvider = "billCases", groups = "utility")
    public void generateBill_returnsFlatRates(String propertyId, UtilityType type, double expected) {
        double bill = service.generateBill(propertyId, type);
        Assert.assertEquals(bill, expected, 0.0001);
    }
}