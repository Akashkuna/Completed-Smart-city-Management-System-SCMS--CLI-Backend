package com.city.service.impl;

import com.city.service.AnalyticsService;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Override public String generateTrafficReport() { return "TRAFFIC_REPORT:OK"; }
    @Override public String generateUtilityConsumptionReport() { return "UTILITY_REPORT:OK"; }
    @Override public String getComplaintStatistics() { return "COMPLAINT_STATS:OK"; }
}