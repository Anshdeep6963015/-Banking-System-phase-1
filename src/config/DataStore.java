package config;

import model.Account;
import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Central in-memory data storage for the banking system.
 */
public class DataStore {
    // Maps userId to User
    public static Map<String, User> users = new HashMap<>();

    // Maps accountNumber to Account
    public static Map<String, Account> accounts = new HashMap<>();

    // All transactions
    public static List<Transaction> transactions = new ArrayList<>();

    // Used for generating unique IDs
    private static int userIdCounter = 1000;
    private static int accountNumberCounter = 5000;
    private static int transactionIdCounter = 9000;

    // Generate unique User ID
    public static String generateUserId() {
        return "U" + (++userIdCounter);
    }

    // Generate unique Account Number
    public static String generateAccountNumber() {
        return "AC" + (++accountNumberCounter);
    }

    // Generate unique Transaction ID
    public static String generateTransactionId() {
        return "TX" + (++transactionIdCounter);
    }

    // Optional: Seed an admin user
    static {
        String adminId = generateUserId();
        users.put(adminId, new User(
                adminId,
                "Admin",
                "admin@bank.com",
                "admin123",
                "admin",
                null
        ));
    }
}
