package bloodfinder.models;

public class BloodRequest {
    private String requestId;
    private String recipientId;
    private String bloodGroup;
    private String urgency;
    private String hospital;
    private String requestDate;
    private String status;

    public BloodRequest(String requestId, String recipientId, String bloodGroup, 
                        String urgency, String hospital, String requestDate) {
        this.requestId = requestId;
        this.recipientId = recipientId;
        this.bloodGroup = bloodGroup;
        this.urgency = urgency;
        this.hospital = hospital;
        this.requestDate = requestDate;
        this.status = "PENDING";
    }

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getHospital() {
        return hospital;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayRequest() {
        System.out.println("\n--- Blood Request ---");
        System.out.println("Request ID: " + requestId);
        System.out.println("Recipient ID: " + recipientId);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Urgency: " + urgency);
        System.out.println("Hospital: " + hospital);
        System.out.println("Date: " + requestDate);
        System.out.println("Status: " + status);
        System.out.println("--------------------\n");
    }
}