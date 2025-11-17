package bloodfinder.controllers;

import bloodfinder.models.Recipient;
import javax.swing.*;
import java.awt.*;

public class RecipientController {
    private MainController mainController;
    
    public RecipientController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void showRecipientRegistration() {
        JFrame frame = new JFrame("🏥 Register as Recipient");
        frame.setSize(500, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(230, 126, 34));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("🏥 RECIPIENT REGISTRATION");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JTextField nameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JComboBox<String> bloodCombo = new JComboBox<>(
            new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}
        );
        JTextField addressField = new JTextField(20);
        JComboBox<String> urgencyCombo = new JComboBox<>(
            new String[]{"HIGH", "MEDIUM", "LOW"}
        );
        JTextField hospitalField = new JTextField(20);
        JTextField dateField = new JTextField(20);
        dateField.setText(bloodfinder.utils.DateUtils.getCurrentDate());
        
        // Add fields
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Blood Group:"), gbc);
        gbc.gridx = 1;
        formPanel.add(bloodCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Urgency:"), gbc);
        gbc.gridx = 1;
        formPanel.add(urgencyCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Hospital:"), gbc);
        gbc.gridx = 1;
        formPanel.add(hospitalField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Request Date:"), gbc);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);
        
        // Register Button
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        
        JButton registerBtn = new JButton("✓ Register Recipient");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerBtn.setBackground(new Color(230, 126, 34));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String bloodGroup = (String) bloodCombo.getSelectedItem();
            String address = addressField.getText().trim();
            String urgency = (String) urgencyCombo.getSelectedItem();
            String hospital = hospitalField.getText().trim();
            String date = dateField.getText().trim();
            
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || hospital.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "Please fill all fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String recipientId = "R" + String.format("%03d", mainController.getAllRecipients().size() + 1);
            Recipient recipient = new Recipient(recipientId, name, bloodGroup, phone, address, urgency, hospital, date);
            mainController.addRecipient(recipient);
            
            JOptionPane.showMessageDialog(frame,
                "✓ Recipient registered successfully!\n\n" +
                "ID: " + recipientId + "\n" +
                "Name: " + name + "\n" +
                "Blood Group: " + bloodGroup + "\n" +
                "Urgency: " + urgency,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            frame.dispose();
        });
        
        formPanel.add(registerBtn, gbc);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}