package bloodfinder.models;

public class DonationHistory {
    private String donationId;
    private String donorId;
    private String recipientId;
    private String donationDate;
    private String location;
    private String bloodGroup;

    public DonationHistory(String donationId, String donorId, String recipientId, 
                          String donationDate, String location, String bloodGroup) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.recipientId = recipientId;
        this.donationDate = donationDate;
        this.location = location;
        this.bloodGroup = bloodGroup;
    }

    // Getters
    public String getDonationId() {
        return donationId;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public String getLocation() {
        return location;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void displayHistory() {
        System.out.println("\n--- Donation Record ---");
        System.out.println("Donation ID: " + donationId);
        System.out.println("Donor ID: " + donorId);
        System.out.println("Recipient ID: " + recipientId);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Date: " + donationDate);
        System.out.println("Location: " + location);
        System.out.println("----------------------\n");
    }
}