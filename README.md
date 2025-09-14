Smart City Management System (SCMS) – CLI Backend

A command-line based backend system that simulates various services of a smart city — including complaint tracking, emergency alerting, traffic monitoring, utility metering, and analytics.

✅ Features
- 📢 Register & update public complaints
- 🚨 Raise & dispatch emergency alerts
- 🚦 Track traffic sensor density & hotspots
- 🔌 Record utility consumption and generate mock bills
- 📊 Generate mock analytics reports
- 🧪 TestNG test suite with full coverage

🧱 Project Structure
com.city
├── App.java
├── model/
│   ├── Citizen, Complaint, EmergencyAlert, etc.
│   └── enums/ (PriorityLevel, AlertStatus, etc.)
├── repository/
│   └── memory/ (InMemoryXxxRepository.java)
├── service/
│   └── impl/ (XxxServiceImpl.java)
└── exception/ (custom RuntimeExceptions)

⚙️ Tech Stack
- Java 17+
- Maven
- TestNG
- IntelliJ IDEA (recommended)
- Mockito (optional)

🚀 How to Run
1. Build the project:
    mvn clean compile

2. Run CLI:
    mvn package
    java -cp target/smart-city-management-1.0.0-SNAPSHOT.jar com.city.App

💻 CLI Menu Options
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

🧪 Running Tests
1. Run full suite:
    mvn test

2. Run from IntelliJ:
    Right-click testng.xml → Run

Test coverage includes:
- ComplaintService
- EmergencyService
- TrafficManagementService
- UtilityMonitoringService
- AnalyticsService
- CitizenRepository

📁 testng.xml
<suite name="SCMS Test Suite">
  <test name="All Services">
    <packages>
      <package name="com.city.service" />
      <package name="com.city.repository" />
    </packages>
  </test>
</suite>

🙋‍♂️ Author
Akash Kuna
Batch: Atlas QAE Capstone
Tech Stack: Java · Maven · TestNG

📎 Notes
- Entire test suite and CLI flow is self-written from scratch
- All services are in-memory, no external DB
- Exceptions are tested and handled cleanly
- Project is modular, testable, and extensible
