package com.city;
import com.city.model.Complaint;
import com.city.model.TrafficSensor;
import com.city.model.enums.*;
import com.city.repository.memory.*;
import com.city.service.*;
import com.city.service.impl.*;
import java.util.List;
import java.util.Scanner;
public class App {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        var complaintRepo = new InMemoryComplaintRepository();
        var emergencyRepo  = new InMemoryEmergencyRepository();
        var trafficRepo    = new InMemoryTrafficRepository();
        var citizenRepo    = new InMemoryCitizenRepository();
        trafficRepo.save(new TrafficSensor("TS-100", "Main Junction"));
        trafficRepo.save(new TrafficSensor("TS-200", "North Bridge"));
        ComplaintService complaintService     = new ComplaintServiceImpl(complaintRepo);
        EmergencyService emergencyService     = new EmergencyServiceImpl(emergencyRepo);
        TrafficManagementService trafficSvc   = new TrafficManagementServiceImpl(trafficRepo);
        UtilityMonitoringService utilitySvc   = new UtilityMonitoringServiceImpl();
        AnalyticsService analyticsService     = new AnalyticsServiceImpl();
        while (true) {
            printMenu();
            int choice = readInt("Choice");
            if (choice == 0) break;
            try {
                switch (choice) {
                    case 1 -> registerComplaint(complaintService);
                    case 2 -> updateComplaintStatus(complaintService);
                    case 3 -> listComplaintsByPriority(complaintService);
                    case 4 -> raiseEmergency(emergencyService);
                    case 5 -> dispatchEmergency(emergencyService);
                    case 6 -> listActiveEmergencies(emergencyService);
                    case 7 -> updateTrafficDensity(trafficSvc);
                    case 8 -> showHighTrafficAreas(trafficSvc);
                    case 9  -> recordUtility(utilitySvc);
                    case 10 -> generateReports(analyticsService);
                    default -> System.out.println("Unknown choice");
                }
            } catch (Exception e) {
                System.out.println("Info: " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("Bye.");
    }
    private static void printMenu() {
        System.out.println("""
        === SCMS CLI ===
        1) Register Complaint
        2) Update Complaint Status
        3) List Complaints by Priority
        4) Raise Emergency
        5) Dispatch Emergency
        6) List Active Emergencies
        7) Update Traffic Density
        8) Show High-Traffic Areas
        9) Record Utility Consumption
        10) Generate Reports
        0) Exit
        """);
    }
    private static int readInt(String label) {
        System.out.print(label + ": ");
        String line = sc.nextLine().trim();
        return Integer.parseInt(line);
    }
    private static String readLine(String label) {
        System.out.print(label + ": ");
        return sc.nextLine();
    }
    private static <E extends Enum<E>> E readEnum(String label, Class<E> e) {
        System.out.print(label + ": ");
        return Enum.valueOf(e, sc.nextLine().trim().toUpperCase());
    }
    private static void registerComplaint(ComplaintService service) {
        String cid = readLine("CitizenId");
        var cat    = readEnum("Category [ROAD/WATER/ELECTRICITY]", ComplaintCategory.class);
        String desc= readLine("Description");
        var pr     = readEnum("Priority [LOW/MEDIUM/HIGH]", PriorityLevel.class);
        Complaint c = service.registerComplaint(cid, cat, desc, pr);
        System.out.println("Complaint Registered: " + c.getComplaintId());
    }
    private static void updateComplaintStatus(ComplaintService service) {
        String id  = readLine("ComplaintId");
        var st     = readEnum("Status [OPEN/IN_PROGRESS/RESOLVED]", ComplaintStatus.class);
        service.updateComplaintStatus(id, st);
        System.out.println("Updated complaint " + id + " -> " + st);
    }
    private static void listComplaintsByPriority(ComplaintService service) {
        var pr = readEnum("Priority [LOW/MEDIUM/HIGH]", PriorityLevel.class);
        List<Complaint> list = service.getComplaintsByPriority(pr);
        if (list.isEmpty()) {
            System.out.println("No complaints with priority " + pr);
        } else {
            list.forEach(c ->
                    System.out.println(c.getComplaintId() + " | " + c.getCategory() + " | " + c.getDescription() + " | " + c.getStatus()));
        }
    }
    private static void raiseEmergency(EmergencyService service) {
        var type = readEnum("Type [FIRE/MEDICAL/SECURITY]", EmergencyType.class);
        String loc = readLine("Location");
        var sev  = readEnum("Severity [LOW/MEDIUM/HIGH]", PriorityLevel.class);
        var alert = service.raiseEmergencyAlert(type, loc, sev);
        System.out.println("Alert Raised: " + alert.getAlertId());
    }
    private static void dispatchEmergency(EmergencyService service) {
        String id = readLine("AlertId");
        service.dispatchEmergencyService(id);
        System.out.println("Dispatched alert " + id);
    }
    private static void listActiveEmergencies(EmergencyService service) {
        var list = service.getActiveEmergencies();
        if (list.isEmpty()) {
            System.out.println("No active emergencies.");
        } else {
            list.forEach(a ->
                    System.out.println(a.getAlertId() + " | " + a.getType() + " | " + a.getLocation() + " | " + a.getSeverity() + " | " + a.getStatus()));
        }
    }
    private static void updateTrafficDensity(TrafficManagementService trafficSvc) {
        String sensorId = readLine("SensorId (e.g., TS-100)");
        int density     = readInt("New Density (int)");
        trafficSvc.updateTrafficDensity(sensorId, density);
        System.out.println("Updated " + sensorId + " to density " + density);
    }
    private static void showHighTrafficAreas(TrafficManagementService trafficSvc) {
        int threshold = readInt("Threshold");
        List<TrafficSensor> list = trafficSvc.getHighTrafficAreas(threshold);
        if (list.isEmpty()) {
            System.out.println("No areas >= " + threshold);
        } else {
            list.forEach(s ->
                    System.out.println(s.getSensorId() + " | " + s.getLocation() + " | density=" + s.getCurrentTrafficDensity()));
        }
    }
    private static void recordUtility(UtilityMonitoringService utilitySvc) {
        String meterId = readLine("MeterId");
        double reading = Double.parseDouble(readLine("Reading"));
        utilitySvc.recordUtilityConsumption(meterId, reading);
        System.out.println("Recorded " + reading + " for meter " + meterId);
    }
    private static void generateReports(AnalyticsService analyticsService) {
        System.out.println(analyticsService.generateTrafficReport());
        System.out.println(analyticsService.generateUtilityConsumptionReport());
        System.out.println(analyticsService.getComplaintStatistics());
    }
}