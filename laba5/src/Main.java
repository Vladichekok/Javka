import java.util.ArrayList;
import java.util.List;
public class BankAccount {
    private int accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(int accountNumber, String accountName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new NegativeAmountException("Amount for deposit cannot be negative");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new NegativeAmountException("Amount for withdrawal cannot be negative");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountSummary() {
        return "Account Number: " + accountNumber + "\nAccount Name: " + accountName + "\nBalance: " + balance;
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) {
        int accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDeposit);
        accounts.add(newAccount);
        return newAccount;
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found with account number: " + accountNumber);
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException, NegativeAmountException {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    private int generateAccountNumber() {
        // Логіка генерації унікального номера рахунку
        return accounts.size() + 1;
    }
}

// Тестовий клас
public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Тестування створення рахунку
        BankAccount account1 = bank.createAccount("John Doe", 1000);
        System.out.println(account1.getAccountSummary());

        // Тестування зняття коштів
        try {
            account1.withdraw(500);
            System.out.println("Withdrawal successful. New balance: " + account1.getBalance());
        } catch (InsufficientFundsException | NegativeAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Тестування переказу коштів
        try {
            BankAccount account2 = bank.createAccount("Jane Doe", 500);
            bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 200);
            System.out.println("Transfer successful. New balances: " +
                    account1.getAccountSummary() + "\n" + account2.getAccountSummary());
        } catch (AccountNotFoundException | InsufficientFundsException | NegativeAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}