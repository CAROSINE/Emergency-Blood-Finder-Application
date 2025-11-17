package bloodfinder.controllers;

import bloodfinder.models.*;
import bloodfinder.services.DonationEligibilityChecker;
import java.util.ArrayList;

public class MainController {
    private ArrayList<Donor> donors;
    private ArrayList<Recipient> recipients;
    private ArrayList<Admin> admins;
    private ArrayList<BloodRequest> requests;
    private ArrayList<DonationHistory> donationHistory;
    private ArrayList<BloodBank> bloodBanks;
    private ArrayList<UserDirectory> userDirectory;
    
    public MainController() {
        donors = new ArrayList<>();
        recipients = new ArrayList<>();
        admins = new ArrayList<>();
        requests = new ArrayList<>();
        donationHistory = new ArrayList<>();
        bloodBanks = new ArrayList<>();
        userDirectory = new ArrayList<>();
        
        initializeSampleData();
        initializeRealStudentData();
    }
    
    private void initializeSampleData() {
        // Sample blood bank
        BloodBank bank = new BloodBank("CSE17", "KYAU CSE- 17th Batch Blood Bank", "Khawja Ynus Ali University", "01799854720");
        bank.addBlood("A+",3 );
        bank.addBlood("A-", 0);
        bank.addBlood("B+", 5);
        bank.addBlood("B-", 0);
        bank.addBlood("AB+",3);
        bank.addBlood("AB-",0);
        bank.addBlood("O+", 5);
        bank.addBlood("O-", 0);
        bloodBanks.add(bank);
    }
    
    private void initializeRealStudentData() {
       
        
        userDirectory.add(new UserDirectory("0622410105101003", "Sazib Kumar Shaha", "B+", "Sirajgonj Sadar", "Batch-2024", 
            "01888521742", "sazibshaha76@gmail.com", "CSE", "20-Sep-2024"));
        donors.add(new Donor("0622410105101003", "Sazib Kumar Shaha", "B+", "01888521742", "Sirajgonj Sadar", "20-Sep-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101005", "Md Shamim Reza", "O+", "Koddar Mor", "Batch-2024", 
            "01868096843", "shamim4491@gmail.com", "CSE", "13-Aug-2024"));
        donors.add(new Donor("0622410105101005", "Md Shamim Reza", "O+", "01868096843", "Koddar Mor", "13-Aug-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101006", "Md. Sumon Hossain Pra", "O+", "Shahzadpur", "Batch-2024", 
                "01518977206", "sumonhossain.off@gmail.com", "CSE", "Never"));
            donors.add(new Donor("0622410105101006", "Md. Sumon Hossain Pra", "O+", "01518977206", "Shahzadpur", "Never"));
        
        userDirectory.add(new UserDirectory("0622410105101007", "Md. Abdullah Al Fahim", "B+", "Belkuchi", "Batch-2024", 
            "01643717679", "alfahimabdullah8@gmail.com", "CSE", "19-Jul-2024"));
        donors.add(new Donor("0622410105101007", "Md. Abdullah Al Fahim", "B+", "01643717679", "Belkuchi", "19-Jul-2024"));
        
        userDirectory.add(new UserDirectory("0622410205101008", "Mst. Ritu Parvin", "B+", "Enayetpur", "Batch-2024", 
            "01319324500", "rituparvin08@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410205101008", "Mst. Ritu Parvin", "B+", "01319324500", "Enayetpur", "Never"));
        
        userDirectory.add(new UserDirectory("0622410205101009", "Tahrim Janin", "AB+", "Enayetpur", "Batch-2024", 
            "01799844720", "jenintahrim@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410205101009", "Tahrim Janin", "AB+", "01799844720", "Enayetpur", "Never"));
        
        userDirectory.add(new UserDirectory("06224102051101012", "Asmaul Husna", "B+", "Sirajgonj Sadar", "Batch-2024", 
            "01311035031", "asmaul1220@gmail.com", "CSE", "Never"));
        donors.add(new Donor("06224102051101012", "Asmaul Husna", "B+", "01311035031", "Sirajgonj Sadar", "Never"));
        
        userDirectory.add(new UserDirectory("0622410105101018", "Md Abdul Mothin", "B+", "Shahzadpur", "Batch-2024", 
            "01799636905", "Sheikhmamotin@gmail.com", "CSE", "13-Oct-2024"));
        donors.add(new Donor("0622410105101018", "Md Abdul Mothin", "B+", "01799636905", "Shahzadpur", "13-Oct-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101019", "Md. Rayhan Ali", "O+", "Shahzadpur", "Batch-2024", 
            "01777080967", "mdrayhanali896@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410105101019", "Md. Rayhan Ali", "O+", "01777080967", "Shahzadpur", "Never"));
        
        userDirectory.add(new UserDirectory("0622410105101025", "Ashik", "AB+", "Sirajgonj Sadar", "Batch-2024", 
            "01406199101", "ashiqurrahman3529@gmail.com", "CSE", "12-Sep-2024"));
        donors.add(new Donor("0622410105101025", "Ashik", "AB+", "01406199101", "Sirajgonj Sadar", "12-Sep-2024"));
        
        userDirectory.add(new UserDirectory("0622410205101033", "Shadia Sultana Oishy", "B+", "Sirajgonj Sadar", "Batch-2024", 
            "01647175312", "sadiasultanaoishy75@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410205101033", "Shadia Sultana Oishy", "B+", "01647175312", "Sirajgonj Sadar", "Never"));
        
        userDirectory.add(new UserDirectory("062241105101037", "Abir Bhattacharya", "B+", "Sirajgonj Sadar", "Batch-2024", 
            "01737230233", "bhattacharyaa936@gmail.com", "CSE", "25-Jul-2024"));
        donors.add(new Donor("062241105101037", "Abir Bhattacharya", "B+", "01737230233", "Sirajgonj Sadar", "25-Jul-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101020", "Mostafizurnur Naim", "A+", "Belkuchi", "Batch-2024", 
            "01321095821", "mdmostafizurrohmannaim@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410105101020", "Mostafizurnur Naim", "A+", "01321095821", "Belkuchi", "Never"));
        
        userDirectory.add(new UserDirectory("0622410105101013", "Md. Habib Khan", "AB+", "Sirajgonj Sadar", "Batch-2024", 
            "01642889008", "mdhabibk760@gmail.com", "CSE", "23-Sep-2024"));
        donors.add(new Donor("0622410105101013", "Md. Habib Khan", "AB+", "01642889008", "Sirajgonj Sadar", "23-Sep-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101014", "A A Zahid Hasan", "O+", "Sirajgonj Sadar", "Batch-2024", 
            "01303415965", "hasanjahid1723@gmail.com", "CSE", "Never"));
        donors.add(new Donor("0622410105101014", "A A Zahid Hasan", "O+", "01303415965", "Sirajgonj Sadar", "Never"));
        
        userDirectory.add(new UserDirectory("0622410105101029", "Md. Morsaline", "O+", "Belkuchi", "Batch-2024", 
            "01736505614", "morsalinekhan692@gmail.com", "CSE", "25-Oct-2024"));
        donors.add(new Donor("0622410105101029", "Md. Morsaline", "O+", "01736505614", "Belkuchi", "25-Oct-2024"));
        
        userDirectory.add(new UserDirectory("0622410105101027", "Naim Talukder", "A+", "Sirajgonj Sadar", "Batch-2024", 
            "01604176894", "ntalukder815@gmail.com", "CSE", "10-Oct-2024"));
        donors.add(new Donor("0622410105101027", "Naim Talukder", "A+", "01604176894", "Sirajgonj Sadar", "10-Oct-2024"));
    }
    
    // Donor operations
    public ArrayList<Donor> searchDonorsByBloodGroup(String bloodGroup) {
        ArrayList<Donor> results = new ArrayList<>();
        for (Donor donor : donors) {
            if (donor.getBloodGroup().equalsIgnoreCase(bloodGroup)) {
                results.add(donor);
            }
        }
        return results;
    }
    
    public void addDonor(Donor donor) {
        donors.add(donor);
    }
    
    public ArrayList<Donor> getAllDonors() {
        return donors;
    }
    
    // Recipient operations
    public void addRecipient(Recipient recipient) {
        recipients.add(recipient);
    }
    
    public ArrayList<Recipient> getAllRecipients() {
        return recipients;
    }
    
    // Blood Bank operations
    public ArrayList<BloodBank> getAllBloodBanks() {
        return bloodBanks;
    }
    
    // User Directory operations
    public ArrayList<UserDirectory> getUserDirectory() {
        return userDirectory;
    }
    
    // Statistics
    public int getTotalDonors() {
        return donors.size();
    }
    
    public int getTotalRecipients() {
        return recipients.size();
    }
    
    public int getTotalUsers() {
        return userDirectory.size();
    }
    
    public int getEligibleDonorsCount() {
        int count = 0;
        for (Donor donor : donors) {
            if (DonationEligibilityChecker.isEligible(donor.getLastDonationDate())) {
                count++;
            }
        }
        return count;
    }
    
    public int getEligibleUsersCount() {
        int count = 0;
        for (UserDirectory user : userDirectory) {
            if (DonationEligibilityChecker.isEligible(user.getLastDonationDate())) {
                count++;
            }
        }
        return count;
    }
}