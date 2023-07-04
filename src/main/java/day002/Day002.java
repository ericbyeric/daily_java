package day002;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Day2 - Formatting a LocalDateTime object
 */
public class Day002 {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formatted = currentDateTime.format(FORMATTER);
        System.out.println("formatted = " + formatted);
    }
}
