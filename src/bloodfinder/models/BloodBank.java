package bloodfinder.models;

public class BloodBank {
    private String bankId;
    private String bankName;
    private String location;
    private String phoneNumber;
    private int aPositive, aNegative, bPositive, bNegative;
    private int abPositive, abNegative, oPositive, oNegative;

    public BloodBank(String bankId, String bankName, String location, String phoneNumber) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.aPositive = 0;
        this.aNegative = 0;
        this.bPositive = 0;
        this.bNegative = 0;
        this.abPositive = 0;
        this.abNegative = 0;
        this.oPositive = 0;
        this.oNegative = 0;
    }

    // Getters
    public String getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Add blood units
    public void addBlood(String bloodGroup, int units) {
        switch (bloodGroup.toUpperCase()) {
            case "A+": aPositive += units; break;
            case "A-": aNegative += units; break;
            case "B+": bPositive += units; break;
            case "B-": bNegative += units; break;
            case "AB+": abPositive += units; break;
            case "AB-": abNegative += units; break;
            case "O+": oPositive += units; break;
            case "O-": oNegative += units; break;
            default: System.out.println("Invalid blood group!");
        }
    }

    // Get available units
    public int getAvailableUnits(String bloodGroup) {
        switch (bloodGroup.toUpperCase()) {
            case "A+": return aPositive;
            case "A-": return aNegative;
            case "B+": return bPositive;
            case "B-": return bNegative;
            case "AB+": return abPositive;
            case "AB-": return abNegative;
            case "O+": return oPositive;
            case "O-": return oNegative;
            default: return 0;
        }
    }

    public void displayInventory() {
        System.out.println("\n=== BLOOD BANK INVENTORY ===");
        System.out.println("Bank: " + bankName);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("\n--- Blood Stock ---");
        System.out.println("A+  : " + aPositive + " units");
        System.out.println("A-  : " + aNegative + " units");
        System.out.println("B+  : " + bPositive + " units");
        System.out.println("B-  : " + bNegative + " units");
        System.out.println("AB+ : " + abPositive + " units");
        System.out.println("AB- : " + abNegative + " units");
        System.out.println("O+  : " + oPositive + " units");
        System.out.println("O-  : " + oNegative + " units");
        System.out.println("===========================\n");
    }
}