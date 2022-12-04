package com.bank.test;

import com.bank.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTests {

    @Test
    public void computeChecking_validate10Periods5Interest() {
        Checking checking = new Checking();
        checking.setInterest(5);
        checking.setPeriods(10);
        checking.setBalance(10000);
        checking.compute();
        assertEquals(16226.0, checking.getBalance(), 1.0);
    }

    @Test
    public void computeSavings_validate10Periods5Interest() {
        Savings savings = new Savings();
        savings.setInterest(5);
        savings.setPeriods(10);
        savings.setBalance(10000);
        savings.compute();
        assertEquals(16288.0, savings.getBalance(), 1.0);
    }

    @Test
    public void computeCD_validate10Periods5Interest() {
        CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit();
        certificateOfDeposit.setInterest(5);
        certificateOfDeposit.setPeriods(10);
        certificateOfDeposit.setBalance(10000);
        certificateOfDeposit.compute();
        assertEquals(16288.0, certificateOfDeposit.getBalance(), 1.0);
    }

    @Test
    public void certificateOfDeposit_setAndValidateTerm() {
        CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit();
        final int MATURITY = 5;
        certificateOfDeposit.setMaturity(5);
        assertEquals(MATURITY, certificateOfDeposit.getMaturity());
    }

    @Test
    public void withdraw5000fromLowestInterestAccount() {
        Account checking = new Checking();
        checking.setBalance(10000);
        checking.setInterest(1);

        Account checking1 = new Checking();
        checking1.setBalance(20000);
        checking1.setInterest(10);

        checking.computeWithdrawal(5000);

        assertEquals(5000, checking.getBalance(), 1.0);
    }

    @Test
    public void reportTotalInterestEarnedInAllAccounts () {
        // Get Total interest from all accounts
        Account savings = new Savings();
        savings.setBalance(100);
        savings.setPeriods(5);
        savings.setInterest(5);

        Account checking = new Checking();
        checking.setBalance(100);
        checking.setPeriods(5);
        checking.setInterest(5);

        Account certificateOfDeposit = new CertificateOfDeposit();
        certificateOfDeposit.setBalance(100);
        certificateOfDeposit.setPeriods(5);
        certificateOfDeposit.setInterest(5);

        Stack<Account> accounts = new Stack<>();
        accounts.add(savings);
        accounts.add(checking);
        accounts.add(certificateOfDeposit);

        Account account = new Account();
        account.computeInterest();

        assertEquals(81444.74, Account.computeInterest(), 1.0);
    }



}
