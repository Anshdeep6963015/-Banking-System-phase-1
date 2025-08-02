package service;

import config.DataStore;
import model.Account;
import model.User;
import util.InputUtil;
import util.ValidationUtil;

import java.time.LocalDate;

/**
 * Handles login and registration logic for users.
 */
public class AuthService {

    /**
     * Authenticate user based on email and password.
     */
    public static User login(String email, String password) {
        for (User user : DataStore.users.values()) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // login failed
    }

    /**
     * Register a new customer and create an account.
     */
    public static void registerCustomer() {
        System.out.println("\n--- New Customer Registration ---");

        String name = InputUtil.readString("Enter your full name: ");
        String email;
        while (true) {
            email = InputUtil.readString("Enter email: ");
            if (ValidationUtil.isValidEmail(email)) break;
            System.out.println("Invalid email format.");
        }

        String password = InputUtil.readString("Set a password: ");

        String accountType = InputUtil.readString("Enter account type (Savings/Current): ");
        double initialDeposit = InputUtil.readDouble("Enter initial deposit amount: ");

        String userId = DataStore.generateUserId();
        String accountNumber = DataStore.generateAccountNumber();

        double interestRate = accountType.equalsIgnoreCase("Savings") ? 3.5 : 0.0;

        Account account = new Account(accountNumber, accountType, initialDeposit, interestRate, LocalDate.now(), true);
        User user = new User(userId, name, email, password, "customer", accountNumber);

        // Save to DataStore
        DataStore.accounts.put(accountNumber, account);
        DataStore.users.put(userId, user);

        System.out.println("✅ Account created successfully!");
        System.out.println("Your User ID: " + userId);
        System.out.println("Your Account Number: " + accountNumber);
    }
}
