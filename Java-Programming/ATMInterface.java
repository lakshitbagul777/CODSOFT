import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option, Scanner scanner) {
        switch (option) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                if (withdrawalAmount > 0) {
                    bankAccount.withdraw(withdrawalAmount);
                } else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
                break;

            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                if (depositAmount > 0) {
                    bankAccount.deposit(depositAmount);
                } else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
                break;

            case 3:
                System.out.println("Your current balance: " + bankAccount.getBalance());
                break;

            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            System.out.println("\nWelcome to the ATM");
            atm.displayOptions();

            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            atm.performTransaction(choice, scanner);
        }
    }
}
