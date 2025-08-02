import java.util.Scanner;

import model.User;
import service.AuthService;
import service.AdminService;
import service.CustomerService;

/**
 * Entry point for the BankMaster system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("        Welcome to BankMaster      ");
        System.out.println("===================================");

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("\nSelect Role:");
            System.out.println("1. Admin");
            System.out.println("2. New Customer (Register)");
            System.out.println("3. Existing Customer (Login)");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    handleAdminLogin(scanner);
                    break;
                case 2:
                    AuthService.registerCustomer();
                    break;
                case 3:
                    handleCustomerLogin(scanner);
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("\nThank you for using BankMaster. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Handles login and dashboard for admin users.
     */
    private static void handleAdminLogin(Scanner scanner) {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter admin email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = AuthService.login(email, password);
        if (user != null && user.getRole().equalsIgnoreCase("admin")) {
            System.out.println("✅ Admin login successful. Welcome, " + user.getName() + "!");
            AdminService.adminMenu(user); // Now implemented
        } else {
            System.out.println("❌ Invalid admin credentials.");
        }
    }

    /**
     * Handles login and dashboard for existing customers.
     */
    private static void handleCustomerLogin(Scanner scanner) {
        System.out.println("\n--- Customer Login ---");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = AuthService.login(email, password);
        if (user != null && user.getRole().equalsIgnoreCase("customer")) {
            System.out.println("✅ Login successful. Welcome, " + user.getName() + "!");
            CustomerService.customerMenu(user); // Now implemented
        } else {
            System.out.println("❌ Invalid customer credentials.");
        }
    }
}
