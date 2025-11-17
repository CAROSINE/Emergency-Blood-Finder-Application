package bloodfinder.models;

public class Recipient extends User {
    private String urgencyLevel;
    private String hospitalName;
    private String requestDate;

    public Recipient(String userId, String name, String bloodGroup, String phoneNumber, 
                     String address, String urgencyLevel, String hospitalName, String requestDate) {
        super(userId, name, bloodGroup, phoneNumber, address);
        this.urgencyLevel = urgencyLevel;
        this.hospitalName = hospitalName;
        this.requestDate = requestDate;
    }

    // Getters and Setters
    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n=== RECIPIENT INFORMATION ===");
        System.out.println(super.toString());
        System.out.println("Urgency: " + urgencyLevel);
        System.out.println("Hospital: " + hospitalName);
        System.out.println("Request Date: " + requestDate);
        System.out.println("============================\n");
    }
}