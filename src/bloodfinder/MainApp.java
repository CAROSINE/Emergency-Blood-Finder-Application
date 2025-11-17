package bloodfinder;
import bloodfinder.controllers.*; 
import bloodfinder.models.*;
import javax.swing.*;
import java.awt.*;  
import java.awt.event.*; 


     
public class MainApp extends JFrame {
    private MainController mainController;
    private DonorController donorController;
    private RecipientController recipientController;
    private UserDirectoryController userDirectoryController;
    
    public MainApp() {
    	
        mainController = new MainController();
        donorController = new DonorController(mainController);
        recipientController = new RecipientController(mainController);
        userDirectoryController = new UserDirectoryController(mainController);
        
        initializeUI(); 
    }
    
    private void initializeUI() {
        setTitle(" Emergency Blood Finder System"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(800, 550); 
        setLocationRelativeTo(null); 
        
        
        // Try applying system look & feel -“try to make the app look like your computer’s default style.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //Main container panel
        JPanel mainPanel = new JPanel(new BorderLayout());//Border layout
        mainPanel.setBackground(new Color(231, 76, 60)); // Red theme background
        
        // Header
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        
        // Menu Panel
        mainPanel.add(createMenuPanel(), BorderLayout.CENTER);
        
        // Footer
        mainPanel.add(createFooter(), BorderLayout.SOUTH);
        
        add(mainPanel); // Add main panel to window frame
        setVisible(true); // Show window for visible
    }
    
    
 // Header section UI
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(192, 57, 43));
        header.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));// Padding(inner space between content and border)
        
        // Icon and Title
        JLabel iconLabel = new JLabel("🩸");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN,70));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
       // Large title
        JLabel title = new JLabel("EMERGENCY BLOOD FINDER");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitle = new JLabel("Save Lives, Donate Blood");
        subtitle.setFont(new Font("Arial", Font.ITALIC, 20));
        subtitle.setForeground(new Color(236, 240, 241));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        // Add items to header panel (proper distribution)
        header.add(iconLabel);
        header.add(Box.createRigidArea(new Dimension(0, 10)));
        header.add(title);
        header.add(Box.createRigidArea(new Dimension(0, 10)));
        header.add(subtitle);
        
        return header;
    }
    
     // Menu with buttons (its create a grid)
    
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(231, 76, 60));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40,40, 40,40));// 40px
        
         // Button placement rules
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Place each new button below the previous one
        gbc.fill = GridBagConstraints.HORIZONTAL;// Make buttons stretch horizontally
        gbc.insets = new Insets(5, 0,5, 0);// Add 5px space above and below each button
        gbc.weightx = 1.0;
        
        
        // Menu Buttons customize
        
        menuPanel.add(createMenuButton("Search Blood Donors", 
            "Find donors by blood group with eligibility check",
            new Color(39, 174, 96), 
            e -> donorController.showDonorSearchWindow()), gbc);
        
        menuPanel.add(createMenuButton("User Directory (17th Batch)", 
            "Browse and search 25 registered users",
            new Color(41, 128, 185), 
            e -> userDirectoryController.showUserDirectory()), gbc);
        
        menuPanel.add(createMenuButton("Register as Donor", 
            "Register yourself as a blood donor",
            new Color(22, 160, 133), 
            e -> showDonorRegistration()), gbc);
        
        menuPanel.add(createMenuButton("Register as Recipient", 
            "Register blood requirement request",
            new Color(230, 126, 34), 
            e -> recipientController.showRecipientRegistration()), gbc);
        
        menuPanel.add(createMenuButton("View Blood Banks", 
            "Check blood bank inventory and availability",
            new Color(52, 73, 94), 
            e -> showBloodBanks()), gbc);
        
        menuPanel.add(createMenuButton("System Statistics", 
            "View system statistics and reports",
            new Color(44, 62, 80), 
            e -> showStatistics()), gbc);
        
        return menuPanel;
    }
    
    private JButton createMenuButton(String text, String description, Color bgColor, ActionListener action) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout(10, 5)); 
        btn.setPreferredSize(new Dimension(500, 70)); 
        btn.setBackground(bgColor);  
        btn.setForeground(Color.WHITE); 
        btn.setFocusPainted(false); // Remove focus outline
        btn.setBorderPainted(false); 
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Hand cursor
        btn.addActionListener(action); // Add button action
        
        // Button content( Inner content panel)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
       // Button title
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
      // Button description
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(236, 240, 241));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(descLabel);
        
        btn.add(contentPanel, BorderLayout.CENTER); //Add content to button
   
        // Hover effect (change the button appearance)
        btn.addMouseListener(new MouseAdapter() { 
            public void mouseEntered(MouseEvent e) { 
                btn.setBackground(bgColor.darker());
                btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
           
            public void mouseExited(MouseEvent e) { // Event triggered when mouse leaves the button area
                btn.setBackground(bgColor); // Reset button background to original color
                btn.setBorder(null);  
            }
        });
        
        return btn;
    }
    
    
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(0, 0, 0, 150));
        footer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel leftLabel = new JLabel("© 2025 Emergency Blood Finder");
        leftLabel.setForeground(Color.WHITE);
        leftLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel centerLabel = new JLabel("Save Lives Every Day 🩸");
        centerLabel.setForeground(Color.WHITE);
        centerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel rightLabel = new JLabel("Version 1.0");
        rightLabel.setForeground(new Color(236, 240, 241));
        rightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        footer.add(leftLabel, BorderLayout.WEST);
        footer.add(centerLabel, BorderLayout.CENTER);
        footer.add(rightLabel, BorderLayout.EAST);
        
        return footer;
    }
    
    
    private void showDonorRegistration() {
        JFrame frame = new JFrame(" + Register as Donor");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(22, 160, 133));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel(" + DONOR REGISTRATION");
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
        JTextField lastDonationField = new JTextField(20);
        lastDonationField.setText("Never");
        
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
        formPanel.add(new JLabel("Last Donation:"), gbc);
        gbc.gridx = 1;
        formPanel.add(lastDonationField, gbc);
        
        JLabel hint = new JLabel("(Format: dd-MMM-yyyy or 'Never')");
        hint.setFont(new Font("Arial", Font.ITALIC, 10));
        gbc.gridy = 5;
        formPanel.add(hint, gbc);
        
        // Register Button
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        
        JButton registerBtn = new JButton("✓ Register Donor");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerBtn.setBackground(new Color(39, 174, 96));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String bloodGroup = (String) bloodCombo.getSelectedItem();
            String address = addressField.getText().trim();
            String lastDonation = lastDonationField.getText().trim();
            
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "Please fill all fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String donorId = "D" + String.format("%03d", mainController.getAllDonors().size() + 1);
            Donor donor = new Donor(donorId, name, bloodGroup, phone, address, lastDonation);
            mainController.addDonor(donor);
            
            JOptionPane.showMessageDialog(frame,
                "✓ Donor registered successfully!\n\n" +
                "ID: " + donorId + "\n" +
                "Name: " + name + "\n" +
                "Blood Group: " + bloodGroup,
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
    
    private void showBloodBanks() {
        JFrame frame = new JFrame("🏦 Blood Banks");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(52, 73, 94));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("BLOOD BANKS INVENTORY");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        for (BloodBank bank : mainController.getAllBloodBanks()) {
            contentPanel.add(createBloodBankCard(bank));
            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private JPanel createBloodBankCard(BloodBank bank) {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Bank Info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel("🏦 " + bank.getBankName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel locationLabel = new JLabel("📍 " + bank.getLocation());
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel phoneLabel = new JLabel("📞 " + bank.getPhoneNumber());
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(locationLabel);
        infoPanel.add(phoneLabel);
        
        // Inventory
        JPanel inventoryPanel = new JPanel(new GridLayout(4, 2, 15, 10));
        inventoryPanel.setBackground(new Color(236, 240, 241));
        inventoryPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
            "Blood Inventory",
            0, 0,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for (String bg : bloodGroups) {
            int units = bank.getAvailableUnits(bg);
            Color statusColor = units > 20 ? new Color(39, 174, 96) : 
                               units > 10 ? new Color(230, 126, 34) : Color.RED;
            
            JLabel bgLabel = new JLabel("🩸 " + bg + ":");
            bgLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            JLabel unitsLabel = new JLabel(units + " units");
            unitsLabel.setFont(new Font("Arial", Font.BOLD, 14));
            unitsLabel.setForeground(statusColor);
            
            inventoryPanel.add(bgLabel);
            inventoryPanel.add(unitsLabel);
        }
        
        card.add(infoPanel, BorderLayout.NORTH);
        card.add(inventoryPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private void showStatistics() {
        String stats = String.format(
            "╔════════════════════════════════════╗\n" +
            "║    SYSTEM STATISTICS               ║\n" +
            "╚════════════════════════════════════╝\n\n" +
            "Total Donors: %d\n" +
            "Total Recipients: %d\n" +
            " Total Users: %d\n" +
            " Eligible Donors: %d\n" +
            "Eligible Users: %d\n" +
            " Blood Banks: %d\n\n" +
            "Thank you for using Emergency Blood Finder!\n" +
            "Together we save lives! ",
            mainController.getTotalDonors(),
            mainController.getTotalRecipients(),
            mainController.getTotalUsers(),
            mainController.getEligibleDonorsCount(),
            mainController.getEligibleUsersCount(),
            mainController.getAllBloodBanks().size()
        );
        
        JTextArea textArea = new JTextArea(stats);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBackground(new Color(236, 240, 241));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JOptionPane.showMessageDialog(this, textArea, 
            "System Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp()); // Safe Swing startup
    }
}