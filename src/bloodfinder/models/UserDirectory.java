package bloodfinder.models;

public class UserDirectory {
    private String userId;
    private String name;
    private String bloodGroup;
    private String location;
    private String batch;
    private String phone;
    private String email;
    private String department;
    private String lastDonationDate;

    public UserDirectory(String userId, String name, String bloodGroup, String location, 
                        String batch, String phone, String email, String department, String lastDonationDate) {
        this.userId = userId;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.batch = batch;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.lastDonationDate = lastDonationDate;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getBloodGroup() { return bloodGroup; }
    public String getLocation() { return location; }
    public String getBatch() { return batch; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getLastDonationDate() { return lastDonationDate; }

    // Setters
    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public void displayInfo() {
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│ User ID       : " + userId);
        System.out.println("│ Name          : " + name);
        System.out.println("│ Blood Group   : " + bloodGroup);
        System.out.println("│ Location      : " + location);
        System.out.println("│ Batch         : " + batch);
        System.out.println("│ Department    : " + department);
        System.out.println("│ Phone         : " + phone);
        System.out.println("│ Email         : " + email);
        System.out.println("│ Last Donation : " + lastDonationDate);
        System.out.println("└──────────────────────────────────────┘");
    }
}
