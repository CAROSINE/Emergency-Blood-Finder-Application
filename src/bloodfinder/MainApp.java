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
    
    // Collapsible menu components
    private JPanel menuPanel;
    private JButton toggleButton;
    private boolean isMenuVisible = true;
    private JPanel contentArea;
    
    // User-Friendly Blood Theme Color Scheme
    private static final Color BG_MAIN = new Color(250, 247, 245);           // Warm off-white background
    private static final Color SIDEBAR_BG = new Color(139, 0, 0);            // Deep blood red sidebar
    private static final Color CARD_BG = new Color(255, 255, 255);           // Pure white cards
    private static final Color HOVER_BG = new Color(178, 34, 34);            // Firebrick red hover
    private static final Color ACCENT_RED = new Color(220, 53, 69);          // Bright red accent
    private static final Color ACCENT_LIGHT = new Color(255, 182, 193);      // Light pink accent
    private static final Color ACCENT_LIGHTER = new Color(255, 228, 225);    // Misty rose
    private static final Color TEXT_DARK = new Color(33, 33, 33);            // Dark text
    private static final Color TEXT_SECONDARY = new Color(108, 117, 125);    // Gray text
    private static final Color BORDER_COLOR = new Color(220, 220, 220);      // Light gray border
    private static final Color SUCCESS_GREEN = new Color(40, 167, 69);       // Success green
    private static final Color WARNING_ORANGE = new Color(255, 193, 7);      // Warning amber
    private static final Color DANGER_RED = new Color(220, 53, 69);          // Danger red
    
    public MainApp() {
        mainController = new MainController();
        donorController = new DonorController(mainController);
        recipientController = new RecipientController(mainController);
        userDirectoryController = new UserDirectoryController(mainController);
        
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Emergency Blood Finder System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 850);
        setLocationRelativeTo(null);
        
        // Set modern Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);
        
        // Header
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        
        // Menu Panel with Toggle Button
        mainPanel.add(createMenuContainer(), BorderLayout.WEST);
        
        // Content Area (Right side)
        contentArea = createWelcomeScreen();
        mainPanel.add(contentArea, BorderLayout.CENTER);
        
        // Footer
        mainPanel.add(createFooter(), BorderLayout.SOUTH);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, SIDEBAR_BG,
                    getWidth(), 0, new Color(178, 34, 34)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Decorative blood drops
                g2d.setColor(new Color(255, 255, 255, 40));
                int[] dropX = {80, 200, 320, getWidth()-320, getWidth()-200, getWidth()-80};
                for (int x : dropX) {
                    g2d.fillOval(x, 15, 10, 10);
                    g2d.fillOval(x+3, 22, 4, 6);
                }
            }
        };
        header.setPreferredSize(new Dimension(0, 90));
        header.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // Left: University name
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel universityLabel = new JLabel("Khwaja Yunus Ali University");
        universityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        universityLabel.setForeground(new Color(255, 255, 255, 180));
        universityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel deptLabel = new JLabel("Blood Donation Management");
        deptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        deptLabel.setForeground(new Color(255, 255, 255, 150));
        deptLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        leftPanel.add(universityLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        leftPanel.add(deptLabel);

        // Center: Title with icon
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel iconLabel = new JLabel("🩸");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));

        JLabel title = new JLabel("EMERGENCY BLOOD FINDER");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        centerPanel.add(iconLabel);
        centerPanel.add(title);

        // Right: Status indicator
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        
        JLabel statusLabel = new JLabel("● SYSTEM ACTIVE");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(new Color(144, 238, 144));
        statusLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JLabel timeLabel = new JLabel("24/7 Available");
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        timeLabel.setForeground(new Color(255, 255, 255, 180));
        timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        rightPanel.add(statusLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        rightPanel.add(timeLabel);

        header.add(leftPanel, BorderLayout.WEST);
        header.add(centerPanel, BorderLayout.CENTER);
        header.add(rightPanel, BorderLayout.EAST);

        return header;
    }

    //////////////// COLLAPSIBLE MENU SECTION ////////////////
    
    private JPanel createMenuContainer() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(SIDEBAR_BG);
        
        // Create toggle button
        toggleButton = createToggleButton();
        
        // Create menu panel
        menuPanel = createMenuPanel();
        
        // Add components
        container.add(toggleButton, BorderLayout.NORTH);
        container.add(menuPanel, BorderLayout.CENTER);
        
        return container;
    }
    
    private JButton createToggleButton() {
        JButton btn = new JButton("☰  Dashboard");
        btn.setPreferredSize(new Dimension(280, 65));
        btn.setBackground(SIDEBAR_BG);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255, 30)),
            BorderFactory.createEmptyBorder(18, 25, 18, 15)
        ));
        
        btn.addActionListener(e -> toggleMenu());
        
        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(HOVER_BG);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_BG);
            }
        });
        
        return btn;
    }
    
    private void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        menuPanel.setVisible(isMenuVisible);
        toggleButton.setText(isMenuVisible ? "☰  Dashboard" : "☰");
        revalidate();
        repaint();
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(SIDEBAR_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        panel.setPreferredSize(new Dimension(280, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1.0;
        
        // Menu section label
        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        menuLabel.setForeground(new Color(255, 255, 255, 150));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        panel.add(menuLabel, gbc);
        
        // Menu Buttons
        panel.add(createMenuButton("Search Blood Donors", 
            "🔍",
            e -> donorController.showDonorSearchWindow()), gbc);
        
        panel.add(createMenuButton("User Directory", 
            "👥",
            e -> userDirectoryController.showUserDirectory()), gbc);
        
        // Separator
        gbc.insets = new Insets(15, 12, 6, 12);
        JLabel regLabel = new JLabel("REGISTRATION");
        regLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        regLabel.setForeground(new Color(255, 255, 255, 150));
        regLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        panel.add(regLabel, gbc);
        
        gbc.insets = new Insets(6, 12, 6, 12);
        panel.add(createMenuButton("Register as Donor", 
            "➕",
            e -> showDonorRegistration()), gbc);
        
        panel.add(createMenuButton("Register as Recipient", 
            "🏥",
            e -> recipientController.showRecipientRegistration()), gbc);
        
        // Separator
        gbc.insets = new Insets(15, 12, 6, 12);
        JLabel infoLabel = new JLabel("INFORMATION");
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        infoLabel.setForeground(new Color(255, 255, 255, 150));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        panel.add(infoLabel, gbc);
        
        gbc.insets = new Insets(6, 12, 6, 12);
        panel.add(createMenuButton("View Blood Banks", 
            "🏦",
            e -> showBloodBanks()), gbc);
        
        panel.add(createMenuButton("System Statistics", 
            "📊",
            e -> showStatistics()), gbc);
        
        // Add spacer at bottom
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);
        
        // Add quick stats at bottom
        gbc.weighty = 0.0;
        gbc.insets = new Insets(20, 12, 10, 12);
        panel.add(createQuickStatsPanel(), gbc);
        
        return panel;
    }
    
    private JButton createMenuButton(String text, String icon, ActionListener action) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout(12, 0));
        btn.setPreferredSize(new Dimension(256, 52));
        btn.setMaximumSize(new Dimension(256, 52));
        btn.setBackground(new Color(178, 34, 34, 100));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 20), 1, true),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        
        // Button content
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        contentPanel.setOpaque(false);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        iconLabel.setForeground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(Color.WHITE);
        
        contentPanel.add(iconLabel);
        contentPanel.add(titleLabel);
        
        btn.add(contentPanel, BorderLayout.CENTER);
        
        // Smooth hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(255, 255, 255, 180));
                btn.setForeground(SIDEBAR_BG);
                iconLabel.setForeground(ACCENT_RED);
                titleLabel.setForeground(SIDEBAR_BG);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 2, true),
                    BorderFactory.createEmptyBorder(11, 14, 11, 14)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(178, 34, 34, 100));
                btn.setForeground(Color.WHITE);
                iconLabel.setForeground(Color.WHITE);
                titleLabel.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 255, 255, 20), 1, true),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
        });
        
        return btn;
    }
    
    private JPanel createQuickStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 0, 0, 30));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 30), 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel("QUICK STATS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        titleLabel.setForeground(new Color(255, 255, 255, 180));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel donorsLabel = new JLabel("👥 Donors: " + mainController.getTotalDonors());
        donorsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        donorsLabel.setForeground(Color.WHITE);
        donorsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel recipientsLabel = new JLabel("🏥 Recipients: " + mainController.getTotalRecipients());
        recipientsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        recipientsLabel.setForeground(Color.WHITE);
        recipientsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel banksLabel = new JLabel("🏦 Banks: " + mainController.getAllBloodBanks().size());
        banksLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        banksLabel.setForeground(Color.WHITE);
        banksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));
        panel.add(donorsLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(recipientsLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(banksLabel);
        
        return panel;
    }
    
    //////////////// WELCOME SCREEN ////////////////
    
    private JPanel createWelcomeScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(CARD_BG);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_LIGHT, 3, true),
            BorderFactory.createEmptyBorder(50, 60, 50, 60)
        ));
        
        // Blood drop icon with shadow effect
        JLabel welcomeIcon = new JLabel("🩸");
        welcomeIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 90));
        welcomeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel welcomeTitle = new JLabel("Welcome to Blood Finder");
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
        welcomeTitle.setForeground(SIDEBAR_BG);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel welcomeSubtitle = new JLabel("Every Drop Counts • Every Donation Saves Lives");
        welcomeSubtitle.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        welcomeSubtitle.setForeground(TEXT_SECONDARY);
        welcomeSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Divider
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(400, 2));
        separator.setForeground(ACCENT_LIGHT);
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Stats with icons
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setOpaque(false);
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        statsPanel.add(Box.createHorizontalGlue());
        statsPanel.add(createWelcomeStatBox("👥", mainController.getTotalDonors(), "Donors"));
        statsPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        statsPanel.add(createWelcomeStatBox("🏥", mainController.getTotalRecipients(), "Recipients"));
        statsPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        statsPanel.add(createWelcomeStatBox("🏦", mainController.getAllBloodBanks().size(), "Blood Banks"));
        statsPanel.add(Box.createHorizontalGlue());
        
        // Call to action
        JLabel ctaLabel = new JLabel("Use the menu to get started →");
        ctaLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        ctaLabel.setForeground(ACCENT_RED);
        ctaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(welcomeIcon);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(welcomeTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(welcomeSubtitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(separator);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(statsPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        contentPanel.add(ctaLabel);
        
        panel.add(contentPanel);
        return panel;
    }
    
    private JPanel createWelcomeStatBox(String icon, int value, String label) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(ACCENT_LIGHTER);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_LIGHT, 2, true),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(String.valueOf(value));
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueLabel.setForeground(ACCENT_RED);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textLabel.setForeground(TEXT_SECONDARY);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        box.add(iconLabel);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        box.add(valueLabel);
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(textLabel);
        
        return box;
    }
    
    //////////////// FOOTER ////////////////
    
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(SIDEBAR_BG);
        footer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(3, 0, 0, 0, ACCENT_RED),
            BorderFactory.createEmptyBorder(12, 30, 12, 30)
        ));
        
        JLabel leftLabel = new JLabel("© 2025 Blood Finder Team");
        leftLabel.setForeground(new Color(255, 255, 255, 200));
        leftLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JLabel centerLabel = new JLabel("🩸 Save Lives Every Day 🩸");
        centerLabel.setForeground(Color.WHITE);
        centerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel rightLabel = new JLabel("Version 2.0 • User Friendly");
        rightLabel.setForeground(new Color(255, 255, 255, 200));
        rightLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        footer.add(leftLabel, BorderLayout.WEST);
        footer.add(centerLabel, BorderLayout.CENTER);
        footer.add(rightLabel, BorderLayout.EAST);
        
        return footer;
    }
    
    //////////////// DONOR REGISTRATION ////////////////
    
    private void showDonorRegistration() {
        JDialog dialog = new JDialog(this, "Donor Registration", true);
        dialog.setSize(750, 800);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(SIDEBAR_BG);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, ACCENT_RED),
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("➕ Become a Blood Donor");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Join our life-saving community today");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        titlePanel.add(subtitleLabel);
        
        header.add(titlePanel, BorderLayout.WEST);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(35, 50, 35, 50),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_LIGHT, 2, true),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
            )
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        
        JTextField nameField = new JTextField(30);
        styleTextField(nameField, "Enter your full name");
        JTextField phoneField = new JTextField(30);
        styleTextField(phoneField, "e.g., +8801XXXXXXXXX");
        JComboBox<String> bloodCombo = new JComboBox<>(
            new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}
        );
        styleComboBox(bloodCombo);
        JTextField addressField = new JTextField(30);
        styleTextField(addressField, "Enter your complete address");
        JTextField lastDonationField = new JTextField(30);
        styleTextField(lastDonationField, "Format: dd-MMM-yyyy or 'Never'");
        lastDonationField.setText("Never");
        
        // Add fields
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createFieldLabel("Full Name *", "👤"), gbc);
        gbc.gridy = 1;
        formPanel.add(nameField, gbc);
        
        gbc.gridy = 2;
        formPanel.add(createFieldLabel("Phone Number *", "📱"), gbc);
        gbc.gridy = 3;
        formPanel.add(phoneField, gbc);
        
        gbc.gridy = 4;
        formPanel.add(createFieldLabel("Blood Group *", "🩸"), gbc);
        gbc.gridy = 5;
        formPanel.add(bloodCombo, gbc);
        
        gbc.gridy = 6;
        formPanel.add(createFieldLabel("Address *", "📍"), gbc);
        gbc.gridy = 7;
        formPanel.add(addressField, gbc);
        
        gbc.gridy = 8;
        formPanel.add(createFieldLabel("Last Donation Date", "📅"), gbc);
        gbc.gridy = 9;
        formPanel.add(lastDonationField, gbc);
        
        // Info note
        JLabel infoLabel = new JLabel("<html><i>* Required fields</i></html>");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoLabel.setForeground(TEXT_SECONDARY);
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 10, 10, 10);
        formPanel.add(infoLabel, gbc);
        
        // Scroll pane for form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 25));
        buttonPanel.setBackground(BG_MAIN);
        
        JButton registerBtn = createStyledButton("✓ Register Now", ACCENT_RED, Color.WHITE);
        JButton cancelBtn = createStyledButton("Cancel", new Color(108, 117, 125), Color.WHITE);
        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String bloodGroup = (String) bloodCombo.getSelectedItem();
            String address = addressField.getText().trim();
            String lastDonation = lastDonationField.getText().trim();
            
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                    "Please fill all required fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String donorId = "D" + String.format("%03d", mainController.getAllDonors().size() + 1);
            Donor donor = new Donor(donorId, name, bloodGroup, phone, address, lastDonation);
            mainController.addDonor(donor);
            
            JOptionPane.showMessageDialog(dialog,
                String.format("✓ Registration Successful!\n\nDonor ID: %s\nName: %s\nBlood Group: %s\n\nThank you for joining us!", 
                donorId, name, bloodGroup),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            dialog.dispose();
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(registerBtn);
        buttonPanel.add(cancelBtn);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    //////////////// BLOOD BANKS ////////////////
    
    private void showBloodBanks() {
        JDialog dialog = new JDialog(this, "Blood Banks Inventory", false);
        dialog.setSize(1100, 850);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(SIDEBAR_BG);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, ACCENT_RED),
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        
        JLabel titleLabel = new JLabel("🏦 Blood Banks & Inventory");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.WEST);
        
        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BG_MAIN);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        for (BloodBank bank : mainController.getAllBloodBanks()) {
            contentPanel.add(createBloodBankCard(bank));
            contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BG_MAIN);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private JPanel createBloodBankCard(BloodBank bank) {
        JPanel card = new JPanel(new BorderLayout(25, 25));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_LIGHT, 3, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        
        // Bank Info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(CARD_BG);
        
        JLabel nameLabel = new JLabel("🏦 " + bank.getBankName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nameLabel.setForeground(SIDEBAR_BG);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel locationLabel = new JLabel("📍 " + bank.getLocation());
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationLabel.setForeground(TEXT_SECONDARY);
        locationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel phoneLabel = new JLabel("📞 " + bank.getPhoneNumber());
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneLabel.setForeground(TEXT_SECONDARY);
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        infoPanel.add(locationLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        infoPanel.add(phoneLabel);
        
        // Inventory Grid
        JPanel inventoryPanel = new JPanel(new GridLayout(2, 4, 18, 18));
        inventoryPanel.setBackground(ACCENT_LIGHTER);
        inventoryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT_RED, 2, true),
                "  Available Blood Units  ",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 15),
                SIDEBAR_BG
            ),
            BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for (String bg : bloodGroups) {
            int units = bank.getAvailableUnits(bg);
            Color statusColor = units > 20 ? SUCCESS_GREEN : 
                               units > 10 ? WARNING_ORANGE : DANGER_RED;
            String status = units > 20 ? "Good" : units > 10 ? "Low" : "Critical";
            
            JPanel unitCard = new JPanel();
            unitCard.setLayout(new BoxLayout(unitCard, BoxLayout.Y_AXIS));
            unitCard.setBackground(CARD_BG);
            unitCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(statusColor, 3, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
            
            JLabel bgLabel = new JLabel(bg);
            bgLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bgLabel.setForeground(TEXT_DARK);
            bgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel unitsLabel = new JLabel(units + " units");
            unitsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            unitsLabel.setForeground(statusColor);
            unitsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel statusLabel = new JLabel(status);
            statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            statusLabel.setForeground(TEXT_SECONDARY);
            statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            unitCard.add(bgLabel);
            unitCard.add(Box.createRigidArea(new Dimension(0, 8)));
            unitCard.add(unitsLabel);
            unitCard.add(Box.createRigidArea(new Dimension(0, 5)));
            unitCard.add(statusLabel);
            
            inventoryPanel.add(unitCard);
        }
        
        card.add(infoPanel, BorderLayout.NORTH);
        card.add(inventoryPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    //////////////// STATISTICS ////////////////
    
    private void showStatistics() {
        JDialog dialog = new JDialog(this, "System Statistics", false);
        dialog.setSize(750, 700);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(SIDEBAR_BG);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, ACCENT_RED),
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        
        JLabel titleLabel = new JLabel("📊 System Statistics");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.WEST);
        
        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BG_MAIN);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));
        
        // Stats cards
        contentPanel.add(createStatCard("Total Donors", String.valueOf(mainController.getTotalDonors()), "👥", SUCCESS_GREEN));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        contentPanel.add(createStatCard("Total Recipients", String.valueOf(mainController.getTotalRecipients()), "🏥", ACCENT_RED));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        contentPanel.add(createStatCard("Total Users", String.valueOf(mainController.getTotalUsers()), "📋", new Color(13, 110, 253)));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        contentPanel.add(createStatCard("Eligible Donors", String.valueOf(mainController.getEligibleDonorsCount()), "✓", SUCCESS_GREEN));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        contentPanel.add(createStatCard("Blood Banks", String.valueOf(mainController.getAllBloodBanks().size()), "🏦", new Color(111, 66, 193)));
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        
        // Thank you message
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(ACCENT_LIGHTER);
        messagePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_LIGHT, 2, true),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel thankYou = new JLabel("🩸 Together we save lives every day!");
        thankYou.setFont(new Font("Segoe UI", Font.BOLD, 18));
        thankYou.setForeground(ACCENT_RED);
        messagePanel.add(thankYou);
        
        contentPanel.add(messagePanel);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private JPanel createStatCard(String label, String value, String icon, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(25, 0));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_LIGHT, 2, true),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        
        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelText.setForeground(TEXT_SECONDARY);
        labelText.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel valueText = new JLabel(value);
        valueText.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueText.setForeground(accentColor);
        valueText.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        textPanel.add(labelText);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(valueText);
        
        card.add(iconLabel, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    //////////////// HELPER METHODS ////////////////
    
    private JLabel createFieldLabel(String text, String icon) {
        JLabel label = new JLabel(icon + "  " + text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(TEXT_DARK);
        return label;
    }
    
    private void styleTextField(JTextField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(CARD_BG);
        field.setForeground(TEXT_DARK);
        field.setCaretColor(ACCENT_RED);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2, true),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setToolTipText(placeholder);
        
        // Focus effects
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_RED, 2, true),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 2, true),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
        });
    }
    
    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        combo.setBackground(CARD_BG);
        combo.setForeground(TEXT_DARK);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(200, 50));
        
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgColor.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }
    
    public static void main(String[] args) {
        // Set system properties for better rendering
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}
