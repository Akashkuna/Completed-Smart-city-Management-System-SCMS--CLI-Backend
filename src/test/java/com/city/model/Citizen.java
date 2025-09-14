package com.city.model;
public class Citizen {
    private final String citizenId;
    private final String name;
    private String address;
    private String contactNumber;
    private String email;
    public Citizen(String citizenId, String name) {
        if (citizenId == null || citizenId.isBlank()) throw new IllegalArgumentException("citizenId required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.citizenId = citizenId;
        this.name = name;
    }
    public String getCitizenId() { return citizenId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public void setAddress(String address) { this.address = address; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
}