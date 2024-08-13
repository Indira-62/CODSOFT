import java.util.Scanner;

public class ATMSystem {

    // Class representing the user's bank account
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }

    // Class representing the ATM
    static class ATM {
        private BankAccount account;
        private Scanner scanner;

        public ATM(BankAccount account) {
            this.account = account;
            this.scanner = new Scanner(System.in);
        }

        public void start() {
            boolean continueRunning = true;

            while (continueRunning) {
                System.out.println("Welcome to the ATM!");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");

                System.out.print("Please choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        continueRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }

            scanner.close();
        }

        private void checkBalance() {
            System.out.println("Your current balance is: $" + account.getBalance());
        }

        private void deposit() {
            System.out.print("Enter amount to deposit: $");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                account.deposit(amount);
                System.out.println("Deposited: $" + amount);
                checkBalance();
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        private void withdraw() {
            System.out.print("Enter amount to withdraw: $");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("Withdrew: $" + amount);
                checkBalance();
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    public static void main(String[] args) {
        // Initialize a bank account with a starting balance
        BankAccount account = new BankAccount(1000.00); // Starting balance of $1000.00

        // Create an ATM instance with the user's bank account
        ATM atm = new ATM(account);

        // Start the ATM
        atm.start();
    }
}
