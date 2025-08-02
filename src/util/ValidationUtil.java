package util;

import config.DataStore;

/**
 * Utility class for validating account number, email, etc.
 */
public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 4;
    }

    public static boolean isValidAccountNumber(String accountNo) {
        return accountNo != null && DataStore.accounts.containsKey(accountNo);
    }

    public static boolean isEmailTaken(String email) {
        return DataStore.users.containsKey(email);
    }

    public static boolean isValidAccountType(String type) {
        return type.equalsIgnoreCase("Savings") || type.equalsIgnoreCase("Current") || type.equalsIgnoreCase("Fixed");
    }
}
