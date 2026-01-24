package com.unittesting;

import com.unittesting.defaultClasses.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BankAccountTest{

    private BankAccount bankAccount;

    @BeforeEach
    void init(){
        bankAccount = new BankAccount(1000);
    }

    @Test
    void testDeposit(){
        bankAccount.deposit(500);
        Assertions.assertEquals(1500, bankAccount.getBalance());
    }

    @Test
     void testWithdraw(){
        bankAccount.withdraw(300);
        Assertions.assertEquals(700, bankAccount.getBalance());
    }

    @Test
    void testWithdrawMoreThanBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(2000));
    }
}
