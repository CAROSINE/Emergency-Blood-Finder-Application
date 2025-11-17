package bloodfinder.services;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.net.URI;

public class PhoneService {
    
    public static void callDonor(String phoneNumber, String donorName, Component parent) {
        try {
            // Clean and format phone number with country code
            String cleanNumber = phoneNumber.replaceAll("[\\s-]", "");
            
            // Add Bangladesh country code if not present
            if (!cleanNumber.startsWith("+880") && !cleanNumber.startsWith("880")) {
                if (cleanNumber.startsWith("0")) {
                    cleanNumber = "+880" + cleanNumber.substring(1);
                } else {
                    cleanNumber = "+880" + cleanNumber;
                }
            } else if (cleanNumber.startsWith("880")) {
                cleanNumber = "+" + cleanNumber;
            }
            
            int choice = JOptionPane.showConfirmDialog(parent,
                "Call " + donorName + "?\n\nPhone: " + phoneNumber + "\n(Will dial: " + cleanNumber + ")",
                "Make a Call",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (choice == JOptionPane.YES_OPTION) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI("tel:" + cleanNumber));
                    showSuccessMessage(parent, "Phone dialer opened!", 
                        "Calling " + donorName + " at " + cleanNumber);
                } else {
                    copyToClipboard(cleanNumber);
                    showInfoMessage(parent, "Desktop not supported", 
                        "Phone number copied to clipboard: " + cleanNumber);
                }
            }
        } catch (Exception e) {
            String cleanNumber = phoneNumber.replaceAll("[\\s-]", "");
            if (cleanNumber.startsWith("0")) {
                cleanNumber = "+880" + cleanNumber.substring(1);
            }
            copyToClipboard(cleanNumber);
            showErrorMessage(parent, "Could not open dialer", 
                "Phone number copied to clipboard: " + cleanNumber);
        }
    }

    public static void sendSMS(String phoneNumber, String message, Component parent) {
        try {
            String cleanNumber = phoneNumber.replaceAll("[\\s-]", "");
            
            // Add Bangladesh country code if not present
            if (!cleanNumber.startsWith("+880") && !cleanNumber.startsWith("880")) {
                if (cleanNumber.startsWith("0")) {
                    cleanNumber = "+880" + cleanNumber.substring(1);
                } else {
                    cleanNumber = "+880" + cleanNumber;
                }
            } else if (cleanNumber.startsWith("880")) {
                cleanNumber = "+" + cleanNumber;
            }
            
            URI smsUri = new URI("sms:" + cleanNumber + "?body=" + 
                               java.net.URLEncoder.encode(message, "UTF-8"));
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(smsUri);
                showSuccessMessage(parent, "SMS Composer Opened", 
                    "Please send the message from your SMS app.\nTo: " + cleanNumber);
            } else {
                copyToClipboard(message + "\n\nPhone: " + cleanNumber);
                showInfoMessage(parent, "SMS not supported", 
                    "Message and phone copied to clipboard.\nPhone: " + cleanNumber);
            }
        } catch (Exception e) {
            String cleanNumber = phoneNumber.replaceAll("[\\s-]", "");
            if (cleanNumber.startsWith("0")) {
                cleanNumber = "+880" + cleanNumber.substring(1);
            }
            copyToClipboard(message + "\n\nPhone: " + cleanNumber);
            showErrorMessage(parent, "SMS Error", 
                "Message copied to clipboard.\nPhone: " + cleanNumber);
        }
    }

    private static void copyToClipboard(String text) {
        try {
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        } catch (Exception e) {
            System.err.println("Failed to copy to clipboard: " + e.getMessage());
        }
    }

    private static void showSuccessMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showInfoMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showErrorMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static String formatPhoneNumber(String phone) {
        String clean = phone.replaceAll("[\\s-]", "");
        
        // Format Bangladesh number with country code
        if (clean.length() == 11 && clean.startsWith("0")) {
            return "+880-" + clean.substring(1, 4) + "-" + 
                   clean.substring(4, 7) + "-" + clean.substring(7);
        } else if (clean.length() == 10) {
            return "+880-" + clean.substring(0, 3) + "-" + 
                   clean.substring(3, 6) + "-" + clean.substring(6);
        }
        
        return phone;
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        
        String phoneRegex = "^01[3-9]\\d{8}$";
        String cleanPhone = phone.replaceAll("[\\s-]", "");
        return cleanPhone.matches(phoneRegex);
    }
}