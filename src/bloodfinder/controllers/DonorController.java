package bloodfinder.controllers;

import bloodfinder.models.Donor;


import bloodfinder.services.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DonorController {
    private MainController mainController;
    
    public DonorController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void showDonorSearchWindow() {
        JFrame frame = new JFrame("🔍 Search Blood Donors");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Header
        JPanel header = createHeader();
        mainPanel.add(header, BorderLayout.NORTH);
        
        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        JLabel label = new JLabel("Select Blood Group:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JComboBox<String> bloodCombo = new JComboBox<>(
            new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}
        );
        bloodCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton searchBtn = new JButton("🔍 Search");
        searchBtn.setBackground(new Color(39, 174, 96));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JCheckBox eligibleCheck = new JCheckBox("Show Eligible Only (4+ months)");
        eligibleCheck.setSelected(true);
        eligibleCheck.setBackground(Color.WHITE);
        
        searchPanel.add(label);
        searchPanel.add(bloodCombo);
        searchPanel.add(searchBtn);
        searchPanel.add(eligibleCheck);
        
        // Results Panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Search Action
        searchBtn.addActionListener(e -> {
            resultsPanel.removeAll();
            String bloodGroup = (String) bloodCombo.getSelectedItem();
            ArrayList<Donor> donors = mainController.searchDonorsByBloodGroup(bloodGroup);
            
            if (donors.isEmpty()) {
                JLabel noResults = new JLabel("❌ No donors found for blood group: " + bloodGroup);
                noResults.setFont(new Font("Arial", Font.BOLD, 16));
                noResults.setForeground(Color.RED);
                noResults.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                resultsPanel.add(noResults);
            } else {
                int eligibleCount = 0;
                for (Donor donor : donors) {
                    boolean isEligible = DonationEligibilityChecker.isEligible(donor.getLastDonationDate());
                    if (!eligibleCheck.isSelected() || isEligible) {
                        resultsPanel.add(createDonorCard(donor, isEligible, frame));
                        resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                        if (isEligible) eligibleCount++;
                    }
                }
                
                if (eligibleCheck.isSelected() && eligibleCount == 0) {
                    JLabel noEligible = new JLabel("❌ No eligible donors. All donated within last 4 months.");
                    noEligible.setFont(new Font("Arial", Font.BOLD, 14));
                    noEligible.setForeground(new Color(230, 126, 34));
                    noEligible.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    resultsPanel.add(noEligible);
                }
            }
            
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(231, 76, 60));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("🔍 SEARCH BLOOD DONORS");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        return header;
    }
    
    private JPanel createDonorCard(Donor donor, boolean isEligible, Component parent) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel("👤 " + donor.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel bloodLabel = new JLabel("🩸 Blood Group: " + donor.getBloodGroup());
        bloodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel phoneLabel = new JLabel("📞 Phone: " + PhoneService.formatPhoneNumber(donor.getPhoneNumber()));
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel addressLabel = new JLabel("📍 Address: " + donor.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lastDonationLabel = new JLabel("🗓️ Last Donation: " + donor.getLastDonationDate());
        lastDonationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel statusLabel = new JLabel(DonationEligibilityChecker.getEligibilityStatus(donor.getLastDonationDate()));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(isEligible ? new Color(39, 174, 96) : Color.RED);
        
        infoPanel.add(nameLabel);
        infoPanel.add(bloodLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(lastDonationLabel);
        infoPanel.add(statusLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton callBtn = createStyledButton("📞 Call", new Color(39, 174, 96));
        callBtn.addActionListener(e -> handleCall(donor, isEligible, parent));
        
        JButton emailBtn = createStyledButton("📧 Email", new Color(52, 152, 219));
        emailBtn.addActionListener(e -> handleEmail(donor, parent));
        
        JButton smsBtn = createStyledButton("💬 SMS", new Color(155, 89, 182));
        smsBtn.addActionListener(e -> handleSMS(donor, parent));
        
        buttonPanel.add(callBtn);
        buttonPanel.add(emailBtn);
        buttonPanel.add(smsBtn);
        
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        return btn;
    }
    
    private void handleCall(Donor donor, boolean isEligible, Component parent) {
        if (!isEligible) {
            int choice = JOptionPane.showConfirmDialog(parent,
                "⚠️ " + donor.getName() + " is NOT eligible to donate yet.\n\n" +
                "Last Donation: " + donor.getLastDonationDate() + "\n" +
                "Next Eligible: " + DonationEligibilityChecker.getNextEligibleDate(donor.getLastDonationDate()) + "\n\n" +
                "Do you still want to call?",
                "Donor Not Eligible",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (choice != JOptionPane.YES_OPTION) return;
        }
        
        PhoneService.callDonor(donor.getPhoneNumber(), donor.getName(), parent);
    }
    
    private void handleEmail(Donor donor, Component parent) {
        EmailService.sendBloodRequestEmail(donor.getName(), "donor@email.com", 
            "Emergency Patient", donor.getBloodGroup(), parent);
    }
    
    private void handleSMS(Donor donor, Component parent) {
        String message = "Urgent blood needed! Blood Group: " + donor.getBloodGroup();
        PhoneService.sendSMS(donor.getPhoneNumber(), message, parent);
    }
}