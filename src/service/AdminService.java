package service;

import config.DataStore;
import model.Account;
import model.Transaction;
import model.User;
import util.InputUtil;


/**
 * Admin features: manage accounts, view reports, and transactions.
 */
public class AdminService {

    public static void adminMenu(User admin) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Edit Account Details");
            System.out.println("3. Close/Remove Account");
            System.out.println("4. Generate Reports");
            System.out.println("5. View All Transactions");
            System.out.println("6. Back to Main Menu");

            int choice = InputUtil.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    displayAllAccounts();
                    break;
                case 2:
                    editAccountDetails();
                    break;
                case 3:
                    closeAccount();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        for (Account acc : DataStore.accounts.values()) {
            System.out.println("Account No: " + acc.getAccountNumber() +
                               ", Type: " + acc.getAccountType() +
                               ", Balance: ₹" + acc.getBalance() +
                               ", Active: " + acc.isActive());
        }
    }

    private static void editAccountDetails() {
        String accNo = InputUtil.readString("Enter account number to edit: ");
        Account acc = DataStore.accounts.get(accNo);

        if (acc != null) {
            System.out.println("Editing account: " + acc.getAccountNumber());
            String newType = InputUtil.readString("New account type (Savings/Current): ");
            double newInterest = InputUtil.readDouble("New interest rate: ");

            acc.setAccountType(newType);
            acc.setInterestRate(newInterest);

            System.out.println("✅ Account updated successfully.");
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    private static void closeAccount() {
        String accNo = InputUtil.readString("Enter account number to close: ");
        Account acc = DataStore.accounts.get(accNo);

        if (acc != null) {
            acc.setActive(false);
            System.out.println("✅ Account closed (marked inactive).");
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    private static void generateReports() {
        int totalAccounts = DataStore.accounts.size();
        double totalFunds = DataStore.accounts.values().stream()
                                 .mapToDouble(Account::getBalance)
                                 .sum();

        System.out.println("\n--- Report ---");
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Total Funds in Bank: ₹" + totalFunds);
    }

    private static void viewAllTransactions() {
        System.out.println("\n--- All Transactions ---");

        if (DataStore.transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : DataStore.transactions) {
            System.out.println("ID: " + t.getTransactionId() +
                               ", Type: " + t.getType() +
                               ", From: " + t.getFromAccount() +
                               ", To: " + t.getToAccount() +
                               ", Amount: ₹" + t.getAmount() +
                               ", Time: " + t.getTimestamp());
        }
    }
}
