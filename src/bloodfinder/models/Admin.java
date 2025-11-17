package bloodfinder.models;

public class Admin extends User {
    private String role;
    private String joinDate;

    public Admin(String userId, String name, String bloodGroup, String phoneNumber, 
                 String address, String role, String joinDate) {
        super(userId, name, bloodGroup, phoneNumber, address);
        this.role = role;
        this.joinDate = joinDate;
    }

    // Getters and Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n=== ADMIN INFORMATION ===");
        System.out.println(super.toString());
        System.out.println("Role: " + role);
        System.out.println("Join Date: " + joinDate);
        System.out.println("========================\n");
    }
}