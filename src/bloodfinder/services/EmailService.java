package bloodfinder.services;

import javax.swing.JOptionPane;
import java.awt.Component;

public class EmailService {
    
    
    public static void sendBloodRequestEmail(String donorName, String donorEmail, 
                                            String recipientName, String bloodGroup, 
                                            Component parent) {
        String subject = "🩸 Urgent Blood Request - " + bloodGroup;
        String body = String.format(
            "Dear %s,\n\n" +
            "We have an urgent blood request matching your blood group (%s).\n\n" +
            "RECIPIENT: %s\n" +
            "BLOOD GROUP: %s\n\n" +
            "If you are available to donate, please contact us immediately.\n\n" +
            "Thank you for being a life saver!\n\n" +
            "Emergency Blood Finder Team",
            donorName, bloodGroup, recipientName, bloodGroup
        );
        
        // Simulated email sending
        int result = JOptionPane.showConfirmDialog(parent,
            "Send email to " + donorName + "?\n\n" +
            "To: " + donorEmail + "\n" +
            "Subject: " + subject,
            "Send Email",
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(parent,
                "✓ Email sent successfully to " + donorName + "!\n\n" +
                "(In production, this would use JavaMail API)",
                "Email Sent",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void sendThankYouEmail(String donorName, String donorEmail, Component parent) {
        String subject = "❤️ Thank You for Donating Blood!";
        String body = String.format(
            "Dear %s,\n\n" +
            "Thank you for your recent blood donation!\n\n" +
            "Your donation has saved lives and made a real difference.\n\n" +
            "Stay healthy and keep saving lives!\n\n" +
            "Emergency Blood Finder Team",
            donorName
        );
        
        JOptionPane.showMessageDialog(parent,
            "✓ Thank you email sent to " + donorName + "!",
            "Email Sent",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }
}