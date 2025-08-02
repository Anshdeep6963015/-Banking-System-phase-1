package service;

import config.DataStore;
import model.Account;
import model.Transaction;
import model.User;
import util.InputUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles customer operations: deposit, withdraw, transfer, view, edit.
 */
public class CustomerService {

    public static void customerMenu(User customer) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Account Details");
            System.out.println("6. View Transaction History");
            System.out.println("7. Edit Account Details");
            System.out.println("8. Logout");

            int choice = InputUtil.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    checkBalance(customer);
                    break;
                case 2:
                    deposit(customer);
                    break;
                case 3:
                    withdraw(customer);
                    break;
                case 4:
                    transfer(customer);
                    break;
                case 5:
                    viewAccount(customer);
                    break;
                case 6:
                    viewTransactions(customer);
                    break;
                case 7:
                    editAccount(customer);
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void checkBalance(User customer) {
        Account acc = getAccount(customer);
        System.out.println("Your current balance: ₹" + acc.getBalance());
    }

    private static void deposit(User customer) {
        Account acc = getAccount(customer);
        double amount = InputUtil.readDouble("Enter amount to deposit: ");
        acc.setBalance(acc.getBalance() + amount);

        logTransaction("Deposit", acc.getAccountNumber(), null, amount, "Self deposit");
        System.out.println("✅ ₹" + amount + " deposited successfully.");
    }

    private static void withdraw(User customer) {
        Account acc = getAccount(customer);
        double amount = InputUtil.readDouble("Enter amount to withdraw: ");

        if (acc.getBalance() >= amount) {
            acc.setBalance(acc.getBalance() - amount);
            logTransaction("Withdraw", acc.getAccountNumber(), null, amount, "Self withdrawal");
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    private static void transfer(User customer) {
        Account senderAcc = getAccount(customer);
        String receiverAccNo = InputUtil.readString("Enter receiver account number: ");
        Account receiverAcc = DataStore.accounts.get(receiverAccNo);

        if (receiverAcc == null || !receiverAcc.isActive()) {
            System.out.println("❌ Receiver account not found or inactive.");
            return;
        }

        double amount = InputUtil.readDouble("Enter amount to transfer: ");
        if (senderAcc.getBalance() < amount) {
            System.out.println("❌ Insufficient balance.");
            return;
        }

        // Transfer money
        senderAcc.setBalance(senderAcc.getBalance() - amount);
        receiverAcc.setBalance(receiverAcc.getBalance() + amount);

        logTransaction("Transfer", senderAcc.getAccountNumber(), receiverAccNo, amount, "Customer fund transfer");
        System.out.println("✅ ₹" + amount + " transferred to " + receiverAccNo + " successfully.");
    }

    private static void viewAccount(User customer) {
        Account acc = getAccount(customer);
        System.out.println("\n--- Account Details ---");
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Account No: " + acc.getAccountNumber());
        System.out.println("Type: " + acc.getAccountType());
        System.out.println("Balance: ₹" + acc.getBalance());
        System.out.println("Interest Rate: " + acc.getInterestRate() + "%");
        System.out.println("Created On: " + acc.getCreatedDate());
        System.out.println("Status: " + (acc.isActive() ? "Active" : "Closed"));
    }

    private static void viewTransactions(User customer) {
        String accNo = customer.getAccountNumber();
        List<Transaction> myTx = DataStore.transactions.stream()
            .filter(t -> accNo.equals(t.getFromAccount()) || accNo.equals(t.getToAccount()))
            .collect(Collectors.toList());

        if (myTx.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        for (Transaction t : myTx) {
            System.out.println("ID: " + t.getTransactionId() +
                               ", Type: " + t.getType() +
                               ", From: " + t.getFromAccount() +
                               ", To: " + t.getToAccount() +
                               ", Amount: ₹" + t.getAmount() +
                               ", Time: " + t.getTimestamp());
        }
    }

    private static void editAccount(User customer) {
        System.out.println("\n--- Edit Account Details ---");
        String newName = InputUtil.readString("Enter new name: ");
        String newPassword = InputUtil.readString("Enter new password: ");

        customer.setName(newName);
        customer.setPassword(newPassword);

        System.out.println("✅ Account details updated.");
    }

    private static void logTransaction(String type, String from, String to, double amount, String description) {
        String txId = DataStore.generateTransactionId();
        Transaction tx = new Transaction(txId, type, from, to, amount, LocalDateTime.now(), description);
        DataStore.transactions.add(tx);
    }

    private static Account getAccount(User user) {
        return DataStore.accounts.get(user.getAccountNumber());
    }
}
