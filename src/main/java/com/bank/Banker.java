package com.bank;

import javax.swing.*;
import java.util.ArrayList;

public class Banker {

    public static String CHECKING = "Checking";
    public static String SAVINGS = "Savings";
    public static String CERTIFICATEOFDEPOSIT = "Certificate Of Deposit";
    //These were final

    private static ArrayList<Account> allAccounts = new ArrayList<>();
    public static void main(String[] args) {
        promptUser();
        displayBalance();
    }

    /**
     * Prompt user to select accountTypes and enter each value desired
     */
    private static void promptUser() {
        int goAgain = JOptionPane.NO_OPTION;
        do {
            String[] accountTypes = {CHECKING, SAVINGS, CERTIFICATEOFDEPOSIT};
            Object accountType = JOptionPane.showInputDialog(null, "Choose an account to create", "Choose an account", JOptionPane.QUESTION_MESSAGE, null, accountTypes, CHECKING);

            Account account = createAccount(accountType);
            String strBeginningBalance = JOptionPane.showInputDialog("Enter Beginning Balance");
            int beginningBalance = Integer.parseInt(strBeginningBalance);
            account.setBalance(beginningBalance);

            String strInterestRate = JOptionPane.showInputDialog("Enter Interest Rate");
            int interestRate = Integer.parseInt(strInterestRate);
            account.setInterest(interestRate);

            String strPeriods = JOptionPane.showInputDialog("Enter Periods");
            int periods = Integer.parseInt(strPeriods);
            account.setPeriods(periods);
            allAccounts.add(account);

            goAgain = JOptionPane.showConfirmDialog(null, "Do you want to enter another account?", "Go Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } while (goAgain == JOptionPane.YES_OPTION);
    }
    /**
     * Simple factory method to create and return a subclass of type Account
     *
     * @param selectedAccount A string representing the account we want to create.
     * @return the created account.
     */
    public static Account createAccount(final Object selectedAccount) {
        Account account = new Account();
        if (selectedAccount.toString().equals(CHECKING)) {
            account = new Checking();
        }
        else if (selectedAccount.toString().equals(SAVINGS)) {
            account = new Savings();
        }
        else if (selectedAccount.toString().equals(CERTIFICATEOFDEPOSIT)) {
            account = new CertificateOfDeposit();
        }
        return account;
    }

    /**
     * Display new balance from compute
     */
    private static void displayBalance() {
            for (Account account : allAccounts) {
                account.compute();
                JOptionPane.showMessageDialog(null, "The account has a balance of: " + account.getBalance());
            }
    }
}
