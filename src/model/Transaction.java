package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a single bank transaction (deposit, withdraw, transfer).
 */
public class Transaction {
    private String transactionId;
    private String type;             // "Deposit", "Withdraw", "Transfer"
    private String fromAccount;      // sender (or self for deposit/withdraw)
    private String toAccount;        // receiver (null for withdraw/deposit)
    private double amount;
    private LocalDateTime timestamp;
    private String description;      // optional notes

    public Transaction(String transactionId, String type, String fromAccount, String toAccount, double amount, LocalDateTime timestamp, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;

        // Automatically write to file when transaction is created
        logTransactionToFile();
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ✨ NEW METHOD: Save transaction to file automatically
    private void logTransactionToFile() {
        String filePath = "data/transactions.txt"; // create 'data' folder if not already present
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format(
                "%s | ID: %s | Type: %s | From: %s | To: %s | Amount: ₹%.2f | Note: %s",
                timestamp, transactionId, type, fromAccount,
                (toAccount == null ? "-" : toAccount),
                amount,
                (description == null ? "-" : description)
            ));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠️ Error logging transaction: " + e.getMessage());
        }
    }
}
