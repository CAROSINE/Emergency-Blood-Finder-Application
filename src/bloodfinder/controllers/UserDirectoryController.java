package bloodfinder.controllers;

import bloodfinder.models.UserDirectory;
import bloodfinder.services.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class UserDirectoryController {
    private MainController mainController;
    
    public UserDirectoryController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void showUserDirectory() {
        JFrame frame = new JFrame("👥 User Directory - 17 Students");
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Header
        JPanel header = createHeader();
        mainPanel.add(header, BorderLayout.NORTH);
        
        // Content
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(236, 240, 241));
        
        // Search Panel
        JPanel searchPanel = createSearchPanel();
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Name", "Blood", "Location", "Batch", "Phone", "Email", "Last Donation", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(52, 73, 94));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Name
        table.getColumnModel().getColumn(2).setPreferredWidth(60);   // Blood
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Location
        table.getColumnModel().getColumn(4).setPreferredWidth(80);   // Batch
        table.getColumnModel().getColumn(5).setPreferredWidth(120);  // Phone
        table.getColumnModel().getColumn(6).setPreferredWidth(180);  // Email
        table.getColumnModel().getColumn(7).setPreferredWidth(100);  // Last Donation
        table.getColumnModel().getColumn(8).setPreferredWidth(120);  // Status
        
        // Status column color coding
        table.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                if (status.startsWith("✓")) {
                    c.setForeground(new Color(39, 174, 96));
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(Color.RED);
                    setFont(getFont().deriveFont(Font.BOLD));
                }
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2));
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Action Panel
        JPanel actionPanel = createActionPanel(table, tableModel, frame);
        contentPanel.add(actionPanel, BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Load initial data
        loadAllUsers(tableModel);
        
        // Search functionality
        JTextField searchField = (JTextField) searchPanel.getComponent(1);
        JButton searchBtn = (JButton) searchPanel.getComponent(2);
        JButton showAllBtn = (JButton) searchPanel.getComponent(3);
        JButton eligibleBtn = (JButton) searchPanel.getComponent(4);
        
        searchBtn.addActionListener(e -> searchUsers(searchField.getText(), tableModel));
        searchField.addActionListener(e -> searchUsers(searchField.getText(), tableModel));
        showAllBtn.addActionListener(e -> loadAllUsers(tableModel));
        eligibleBtn.addActionListener(e -> loadEligibleUsers(tableModel));
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("👥 USER DIRECTORY");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("17 CSE Students | Search by Name, Blood Group, Location, or ID");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(new Color(236, 240, 241));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        header.add(title);
        header.add(Box.createRigidArea(new Dimension(0, 5)));
        header.add(subtitle);
        
        return header;
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton searchBtn = createStyledButton("🔍 Search", new Color(52, 152, 219));
        JButton showAllBtn = createStyledButton("👁️ Show All", new Color(39, 174, 96));
        JButton eligibleBtn = createStyledButton("✓ Eligible Only", new Color(22, 160, 133));
        
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchBtn);
        panel.add(showAllBtn);
        panel.add(eligibleBtn);
        
        return panel;
    }
    
    private JPanel createActionPanel(JTable table, DefaultTableModel model, Component parent) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(new Color(236, 240, 241));
        
        JButton callBtn = createStyledButton("📞 Call Selected", new Color(39, 174, 96));
        callBtn.addActionListener(e -> callSelectedUser(table, model, parent));
        
        JButton emailBtn = createStyledButton("📧 Send Email", new Color(52, 152, 219));
        emailBtn.addActionListener(e -> emailSelectedUser(table, model, parent));
        
        JButton smsBtn = createStyledButton("💬 Send SMS", new Color(155, 89, 182));
        smsBtn.addActionListener(e -> smsSelectedUser(table, model, parent));
        
        JButton detailsBtn = createStyledButton("👁️ View Details", new Color(52, 73, 94));
        detailsBtn.addActionListener(e -> viewUserDetails(table, model, parent));
        
        panel.add(callBtn);
        panel.add(emailBtn);
        panel.add(smsBtn);
        panel.add(detailsBtn);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(150, 40));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }
    
    private void loadAllUsers(DefaultTableModel model) {
        model.setRowCount(0);
        for (UserDirectory user : mainController.getUserDirectory()) {
            addUserToTable(user, model);
        }
    }
    
    private void loadEligibleUsers(DefaultTableModel model) {
        model.setRowCount(0);
        for (UserDirectory user : mainController.getUserDirectory()) {
            if (DonationEligibilityChecker.isEligible(user.getLastDonationDate())) {
                addUserToTable(user, model);
            }
        }
    }
    
    private void searchUsers(String query, DefaultTableModel model) {
        if (query == null || query.trim().isEmpty()) {
            loadAllUsers(model);
            return;
        }
        
        model.setRowCount(0);
        String lowerQuery = query.toLowerCase();
        
        for (UserDirectory user : mainController.getUserDirectory()) {
            if (user.getName().toLowerCase().contains(lowerQuery) ||
                user.getBloodGroup().toLowerCase().contains(lowerQuery) ||
                user.getLocation().toLowerCase().contains(lowerQuery) ||
                user.getBatch().toLowerCase().contains(lowerQuery) ||
                user.getUserId().toLowerCase().contains(lowerQuery)) {
                addUserToTable(user, model);
            }
        }
    }
    
    private void addUserToTable(UserDirectory user, DefaultTableModel model) {
        boolean isEligible = DonationEligibilityChecker.isEligible(user.getLastDonationDate());
        String status = isEligible ? "✓ ELIGIBLE" : "✗ NOT ELIGIBLE";
        
        model.addRow(new Object[]{
            user.getUserId(),
            user.getName(),
            user.getBloodGroup(),
            user.getLocation(),
            user.getBatch(),
            PhoneService.formatPhoneNumber(user.getPhone()),
            user.getEmail(),
            user.getLastDonationDate(),
            status
        });
    }
    
    private void callSelectedUser(JTable table, DefaultTableModel model, Component parent) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(parent, "Please select a user first!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String phone = (String) model.getValueAt(selectedRow, 5);
        String name = (String) model.getValueAt(selectedRow, 1);
        String lastDonation = (String) model.getValueAt(selectedRow, 7);
        
        if (!DonationEligibilityChecker.isEligible(lastDonation)) {
            int result = JOptionPane.showConfirmDialog(parent,
                "⚠️ " + name + " donated recently.\n\n" +
                "Last Donation: " + lastDonation + "\n" +
                "Next Eligible: " + DonationEligibilityChecker.getNextEligibleDate(lastDonation) + "\n\n" +
                "Do you still want to call?",
                "Recent Donation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (result != JOptionPane.YES_OPTION) return;
        }
        
        // Remove formatting for actual call
        String rawPhone = phone.replaceAll("[^0-9]", "");
        if (rawPhone.startsWith("880")) {
            rawPhone = "0" + rawPhone.substring(3);
        }
        
        PhoneService.callDonor(rawPhone, name, parent);
    }
    
    private void emailSelectedUser(JTable table, DefaultTableModel model, Component parent) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(parent, "Please select a user first!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String name = (String) model.getValueAt(selectedRow, 1);
        String email = (String) model.getValueAt(selectedRow, 6);
        String bloodGroup = (String) model.getValueAt(selectedRow, 2);
        
        EmailService.sendBloodRequestEmail(name, email, "Patient", bloodGroup, parent);
    }
    
    private void smsSelectedUser(JTable table, DefaultTableModel model, Component parent) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(parent, "Please select a user first!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String phone = (String) model.getValueAt(selectedRow, 5);
        String bloodGroup = (String) model.getValueAt(selectedRow, 2);
        
        // Remove formatting for SMS
        String rawPhone = phone.replaceAll("[^0-9]", "");
        if (rawPhone.startsWith("880")) {
            rawPhone = "0" + rawPhone.substring(3);
        }
        
        PhoneService.sendSMS(rawPhone, "Urgent blood needed! Blood Group: " + bloodGroup, parent);
    }
    
    private void viewUserDetails(JTable table, DefaultTableModel model, Component parent) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(parent, "Please select a user first!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String details = String.format(
            "╔════════════════════════════════════╗\n" +
            "║        USER DETAILS                ║\n" +
            "╚════════════════════════════════════╝\n\n" +
            "🆔 ID: %s\n" +
            "👤 Name: %s\n" +
            "🩸 Blood Group: %s\n" +
            "📍 Location: %s\n" +
            "🎓 Batch: %s\n" +
            "📞 Phone: %s\n" +
            "📧 Email: %s\n" +
            "🗓️ Last Donation: %s\n\n" +
            "%s",
            model.getValueAt(selectedRow, 0),
            model.getValueAt(selectedRow, 1),
            model.getValueAt(selectedRow, 2),
            model.getValueAt(selectedRow, 3),
            model.getValueAt(selectedRow, 4),
            model.getValueAt(selectedRow, 5),
            model.getValueAt(selectedRow, 6),
            model.getValueAt(selectedRow, 7),
            model.getValueAt(selectedRow, 8)
        );
        
        JTextArea textArea = new JTextArea(details);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        textArea.setEditable(false);
        textArea.setBackground(new Color(236, 240, 241));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JOptionPane.showMessageDialog(parent, textArea, "User Details", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}