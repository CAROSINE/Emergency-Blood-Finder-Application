package bloodfinder.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DonationEligibilityChecker {
    private static final int MINIMUM_GAP_MONTHS = 4;
    private static final DateTimeFormatter INPUT_FORMAT = 
        DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    public static boolean isEligible(String lastDonationDate) {
        if (lastDonationDate == null || lastDonationDate.equalsIgnoreCase("Never")) {
            return true;
        }
        
        try {
            LocalDate lastDonation = LocalDate.parse(lastDonationDate, INPUT_FORMAT);
            LocalDate today = LocalDate.now();
            long monthsBetween = ChronoUnit.MONTHS.between(lastDonation, today);
            return monthsBetween >= MINIMUM_GAP_MONTHS;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getNextEligibleDate(String lastDonationDate) {
        if (lastDonationDate == null || lastDonationDate.equalsIgnoreCase("Never")) {
            return "Immediately";
        }
        
        try {
            LocalDate lastDonation = LocalDate.parse(lastDonationDate, INPUT_FORMAT);
            LocalDate nextEligibleDate = lastDonation.plusMonths(MINIMUM_GAP_MONTHS);
            return nextEligibleDate.format(INPUT_FORMAT);
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static long getDaysUntilEligible(String lastDonationDate) {
        if (lastDonationDate == null || lastDonationDate.equalsIgnoreCase("Never")) {
            return 0;
        }
        
        try {
            LocalDate lastDonation = LocalDate.parse(lastDonationDate, INPUT_FORMAT);
            LocalDate nextEligibleDate = lastDonation.plusMonths(MINIMUM_GAP_MONTHS);
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(today, nextEligibleDate);
            return days > 0 ? days : 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getEligibilityStatus(String lastDonationDate) {
        if (lastDonationDate == null || lastDonationDate.equalsIgnoreCase("Never")) {
            return "✓ ELIGIBLE - Never donated";
        }
        
        if (isEligible(lastDonationDate)) {
            long months = ChronoUnit.MONTHS.between(
                LocalDate.parse(lastDonationDate, INPUT_FORMAT), 
                LocalDate.now()
            );
            return "✓ ELIGIBLE - " + months + " months ago";
        } else {
            long days = getDaysUntilEligible(lastDonationDate);
            return "✗ NOT ELIGIBLE - Wait " + days + " days";
        }
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(INPUT_FORMAT);
    }
}