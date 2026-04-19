package LakeBankApp;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// CLASS: BankApp
// PURPOSE: Menu-driven banking application that manages multiple customer accounts
public class BankApp {

    public static void main(String[] args) {

        // SCANNER OBJECT FOR USER INPUT
        Scanner input = new Scanner(System.in);

        // ARRAYLIST STORES ALL CUSTOMER ACCOUNTS CREATED DURING PROGRAM EXECUTION
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // CONTROLS THE MAIN PROGRAM LOOP
        boolean running = true;

        System.out.println("===== WELCOME TO THE LAKE BANK APP =====");

        // MAIN MENU LOOP
        while (running) {
            try {
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1. Create Account");
                System.out.println("2. Access Existing Account");
                System.out.println("3. View All Accounts");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine(); // CLEAR BUFFER AFTER INTEGER INPUT

                switch (choice) {
                    case 1:
                        createAccount(input, accounts);
                        break;

                    case 2:
                        accessAccount(input, accounts);
                        break;

                    case 3:
                        viewAllAccounts(accounts);
                        break;

                    case 4:
                        System.out.println("Thank you for using the Bank App.");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid menu choice. Please enter 1 through 4.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // CLEAR INVALID INPUT FROM SCANNER
            }
        }

        // CLOSE SCANNER TO PREVENT RESOURCE LEAK
        input.close();
    }

 // METHOD: createAccount
 // PURPOSE: Collects user information, confirms PIN, generates account number,
 // and adds the new account to the system
 public static void createAccount(Scanner input, ArrayList<BankAccount> accounts) {

     try {
         System.out.print("Enter account holder name: ");
         String name = input.nextLine();

         // PIN CONFIRMATION LOOP
         int pin = 0;
         boolean pinConfirmed = false;

         while (!pinConfirmed) {

             System.out.print("Create a 4-digit PIN: ");
             int firstPin = input.nextInt();

             System.out.print("Confirm your PIN: ");
             int confirmPin = input.nextInt();
             input.nextLine(); // CLEAR BUFFER

             // CHECK IF BOTH ENTRIES MATCH
             if (firstPin == confirmPin) {

                 // VALIDATE PIN RANGE
                 if (firstPin < 1000 || firstPin > 9999) {
                     System.out.println("PIN must be a 4-digit number.\n");
                 } else {
                     pin = firstPin;
                     pinConfirmed = true;
                 }

             } else {
                 System.out.println("PINs do not match. Please try again.\n");
             }
         }

         System.out.print("Enter starting balance: ");
         double startingBalance = input.nextDouble();
         input.nextLine(); // CLEAR BUFFER

         // GENERATE UNIQUE ACCOUNT NUMBER
         int accountNumber = generateAccountNumber(accounts);

         // CREATE ACCOUNT
         BankAccount newAccount = new BankAccount(name, accountNumber, pin, startingBalance);
         accounts.add(newAccount);

         System.out.println("\nAccount created successfully!");
         System.out.println("Your account number is: " + accountNumber);
         System.out.println("Please save your account number and PIN for login.");

     } catch (Exception e) {
         System.out.println("Error creating account: " + e.getMessage());
         input.nextLine();
     }
 }

    // METHOD: accessAccount
    // PURPOSE: Locates an account by account number, verifies the user's PIN,
    // and opens the account menu if login succeeds
    public static void accessAccount(Scanner input, ArrayList<BankAccount> accounts) {

        try {
            // PREVENT LOGIN ATTEMPT IF NO ACCOUNTS EXIST
            if (accounts.isEmpty()) {
                System.out.println("No accounts exist yet.");
                return;
            }

            System.out.print("Enter account number: ");
            int accountNumber = input.nextInt();
            input.nextLine(); // CLEAR BUFFER

            // SEARCH FOR MATCHING ACCOUNT
            BankAccount selectedAccount = findAccount(accounts, accountNumber);

            if (selectedAccount == null) {
                System.out.println("Account not found.");
                return;
            }

            // LIMIT NUMBER OF PIN ATTEMPTS FOR BASIC SECURITY
            int attempts = 0;
            boolean authenticated = false;

            while (attempts < 3 && !authenticated) {
                System.out.print("Enter 4-digit PIN: ");
                int enteredPin = input.nextInt();
                input.nextLine(); // CLEAR BUFFER

                if (selectedAccount.verifyPin(enteredPin)) {
                    authenticated = true;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN.");

                    if (attempts < 3) {
                        System.out.println("Attempts remaining: " + (3 - attempts));
                    }
                }
            }

            // DENY ACCESS IF USER FAILS TOO MANY TIMES
            if (!authenticated) {
                System.out.println("Too many failed login attempts. Access denied.");
                return;
            }

            System.out.println("Login successful. Welcome, " + selectedAccount.getAccountHolder() + "!");
            accountMenu(input, selectedAccount);

        } catch (Exception e) {
            System.out.println("Error accessing account: " + e.getMessage());
            input.nextLine(); // CLEAR INVALID INPUT
        }
    }

    // METHOD: generateAccountNumber
    // PURPOSE: Generates a random unique 8-digit account number
    // RETURNS: A unique account number not already used by another account
    public static int generateAccountNumber(ArrayList<BankAccount> accounts) {

        Random rand = new Random();
        int accountNumber;

        // KEEP GENERATING UNTIL A UNIQUE ACCOUNT NUMBER IS FOUND
        do {
            accountNumber = 10000000 + rand.nextInt(90000000);
        } while (findAccount(accounts, accountNumber) != null);

        return accountNumber;
    }

    // METHOD: findAccount
    // PURPOSE: Searches the account list for a matching account number
    // RETURNS: Matching BankAccount object if found, otherwise null
    public static BankAccount findAccount(ArrayList<BankAccount> accounts, int accountNumber) {

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    // METHOD: viewAllAccounts
    // PURPOSE: Displays a summary of all accounts currently in the system
    public static void viewAllAccounts(ArrayList<BankAccount> accounts) {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");
        for (BankAccount account : accounts) {
            account.displaySummary();
        }
    }

    // METHOD: accountMenu
    // PURPOSE: Displays account-specific actions after a successful login
    public static void accountMenu(Scanner input, BankAccount account) {

        boolean loggedIn = true;

        while (loggedIn) {
            try {
                System.out.println("\n===== ACCOUNT MENU =====");
                System.out.println("1. Deposit Funds");
                System.out.println("2. Withdraw Funds");
                System.out.println("3. View Balance");
                System.out.println("4. View Account Information");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine(); // CLEAR BUFFER

                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = input.nextDouble();
                        input.nextLine();
                        account.deposit(depositAmount);
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = input.nextDouble();
                        input.nextLine();
                        account.withdraw(withdrawalAmount);
                        break;

                    case 3:
                        System.out.printf("Current balance: $%.2f%n", account.getBalance());
                        break;

                    case 4:
                        account.displayAccountInfo();
                        break;

                    case 5:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        break;

                    default:
                        System.out.println("Invalid menu choice. Please enter 1 through 5.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // CLEAR INVALID INPUT
            }
        }
    }
}