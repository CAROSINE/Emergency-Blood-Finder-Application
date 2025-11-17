package bloodfinder.utils;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    private static final DateTimeFormatter INPUT_FORMAT = 
        DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter DISPLAY_FORMAT = 
        DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

    public static String getCurrentDate() {
        return LocalDate.now().format(INPUT_FORMAT);
    }

    public static String formatDateForDisplay(String date) {
        if (date == null || date.equalsIgnoreCase("Never")) {
            return "Never";
        }
        
        try {
            LocalDate localDate = LocalDate.parse(date, INPUT_FORMAT);
            return localDate.format(DISPLAY_FORMAT);
        } catch (Exception e) {
            return date;
        }
    }

    public static boolean isValidDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate.parse(date, INPUT_FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, INPUT_FORMAT);
        } catch (Exception e) {
            return null;
        }
    }
}