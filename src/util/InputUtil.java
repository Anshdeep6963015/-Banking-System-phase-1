package util;

import java.util.Scanner;

/**
 * Utility class for reading input from console with basic validation.
 */
public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid decimal number. Try again.");
            }
        }
    }

    public static boolean readYesNo(String prompt) {
        while (true) {
            String input = readString(prompt + " (y/n): ");
            if (input.equalsIgnoreCase("y")) return true;
            else if (input.equalsIgnoreCase("n")) return false;
            else System.out.println("❌ Enter 'y' or 'n' only.");
        }
    }
}
