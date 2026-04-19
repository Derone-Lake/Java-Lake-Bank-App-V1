package LakeBankApp;

// CLASS: BankAccount
// PURPOSE: Stores one customer's banking information and handles account actions
public class BankAccount {

    // INSTANCE VARIABLES
    private String accountHolder;
    private int accountNumber;
    private int pin;
    private double balance;

    // CONSTRUCTOR
    // PURPOSE: Creates a new bank account with validated starting data
    public BankAccount(String accountHolder, int accountNumber, int pin, double balance) throws Exception {

        // VALIDATE ACCOUNT HOLDER NAME
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new Exception("Account holder name cannot be blank.");
        }

        // VALIDATE ACCOUNT NUMBER
        if (accountNumber <= 0) {
            throw new Exception("Account number must be greater than 0.");
        }

        // VALIDATE PIN
        // THIS VERSION REQUIRES A 4-DIGIT PIN
        if (pin < 1000 || pin > 9999) {
            throw new Exception("PIN must be a 4-digit number.");
        }

        // VALIDATE STARTING BALANCE
        if (balance < 0) {
            throw new Exception("Starting balance cannot be negative.");
        }

        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    // METHOD: deposit
    // PURPOSE: Adds money to the account balance
    public void deposit(double amount) throws Exception {

        // VALIDATE DEPOSIT AMOUNT
        if (amount <= 0) {
            throw new Exception("Deposit amount must be greater than 0.");
        }

        balance += amount;
        System.out.printf("Deposit successful. New balance: $%.2f%n", balance);
    }

    // METHOD: withdraw
    // PURPOSE: Removes money from the account if funds are available
    public void withdraw(double amount) throws Exception {

        // VALIDATE WITHDRAWAL AMOUNT
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be greater than 0.");
        }

        // PREVENT OVERDRAFTS
        if (amount > balance) {
            throw new Exception("Insufficient funds. Withdrawal denied.");
        }

        balance -= amount;
        System.out.printf("Withdrawal successful. New balance: $%.2f%n", balance);
    }

    // METHOD: verifyPin
    // PURPOSE: Checks if the entered PIN matches the account PIN
    // RETURNS: true if correct, false if incorrect
    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    // METHOD: getBalance
    // PURPOSE: Returns the current balance
    public double getBalance() {
        return balance;
    }

    // METHOD: getAccountHolder
    // PURPOSE: Returns the account holder's name
    public String getAccountHolder() {
        return accountHolder;
    }

    // METHOD: getAccountNumber
    // PURPOSE: Returns the account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // METHOD: displayAccountInfo
    // PURPOSE: Displays detailed account information
    public void displayAccountInfo() {
        System.out.println("\n===== ACCOUNT INFORMATION =====");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Current Balance: $%.2f%n", balance);
    }

    // METHOD: displaySummary
    // PURPOSE: Displays a short summary used when listing all accounts
    public void displaySummary() {
        System.out.printf("Account Number: %d | Holder: %s | Balance: $%.2f%n",
                accountNumber, accountHolder, balance);
    }
}