package bloodfinder.models;

public abstract class User {
    private String userId;
    private String name;
    private String bloodGroup;
    private String phoneNumber;
    private String address;

    public User(String userId, String name, String bloodGroup, String phoneNumber, String address) {
        this.userId = userId;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Abstract method
    public abstract void displayInfo();

    @Override
    public String toString() {
        return "ID: " + userId + ", Name: " + name + ", Blood Group: " + bloodGroup + 
               ", Phone: " + phoneNumber + ", Address: " + address;
    }
}