package model;

import java.time.LocalDate;

/**
 * Represents a bank account.
 */
public class Account {
    private String accountNumber;
    private String accountType;     // e.g., "Savings", "Current"
    private double balance;
    private double interestRate;    // For savings, can be 0 for current
    private LocalDate createdDate;
    private boolean isActive;

    public Account(String accountNumber, String accountType, double balance, double interestRate, LocalDate createdDate, boolean isActive) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.interestRate = interestRate;
        this.createdDate = createdDate;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
