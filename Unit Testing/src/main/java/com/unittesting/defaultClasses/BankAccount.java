package com.unittesting.defaultClasses;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}


/*
BankAccount class:
*.  Using BankAccount class, write test cases using @BeforeEach.
*.  Test: deposit(), withdraw() and withdraw more than balance.
*.  Verify final balance
 */
