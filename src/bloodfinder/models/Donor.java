package bloodfinder.models;

public class Donor extends User {
    private String lastDonationDate;
    private boolean isAvailable;
    private int totalDonations;

    public Donor(String userId, String name, String bloodGroup, String phoneNumber, 
                 String address, String lastDonationDate) {
        super(userId, name, bloodGroup, phoneNumber, address);
        this.lastDonationDate = lastDonationDate;
        this.isAvailable = true;
        this.totalDonations = 0;
    }

    // Getters and Setters
    public String getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getTotalDonations() {
        return totalDonations;
    }

    public void incrementDonations() {
        this.totalDonations++;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n=== DONOR INFORMATION ===");
        System.out.println(super.toString());
        System.out.println("Last Donation: " + lastDonationDate);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Total Donations: " + totalDonations);
        System.out.println("========================\n");
    }
}