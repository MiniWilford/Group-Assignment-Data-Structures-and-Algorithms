package com.bank;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Account extends Banker implements Serializable, Comparable<Account> {
    //Extended banker for Savings, Checking, and Certificate

    private double balance;
    private double interest;
    private int periods;

    private String accountNumber;



    /**
     * Gets total of money in balance to be set for an account...
     * @return entered balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     *  Represents the total of money in an account
     *
     * @param balance of account
     * @return new balance of account
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest;
    }

    /**
     * Set interest of specified account
     *
     * @param interest represented as a double for amount of interest we want
     * @return interest of created account
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getPeriods() {
        return periods;
    }

    /**
     * Set period specified by user for account created
     *
     * @param periods represented for account
     * @return periods for created account
     */
    public void setPeriods(int periods) {
        this.periods = periods;
    }

    /**
    * Compute accounts total interest rate compounded with the amount of periods specified
    *
    */
    public void compute() {
        for(int i = 0; i < getPeriods(); i++) {
            double newBalance = (getInterest() * getBalance());
            setBalance(getBalance() + (newBalance / 100));
        }
    }

    /**
     * Override toString method to return account amount, periods, and interest entered
     * @return account balance, account periods, and account interest
     */
    @Override
    public String toString() {return "Account has $" + getBalance() + " after " + getPeriods() + " periods. With interest of %" + getInterest(); }

        private String priority;

    /**
     * priorities hashmap that assigns a string value to a number value to compare priorities amongst account types
     */
    private static Map<String, Integer> priorities = new HashMap<>();

        static {
            priorities.put(CHECKING, 1);
            priorities.put(SAVINGS, 2);
            priorities.put(CERTIFICATEOFDEPOSIT, 3);
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

    /**
     * Overiding Comparable<Account> interface to compare priority of user bank accounts
     * @param o the account to be compared
     * @return accounts priority
     */
    @Override
        public int compareTo(Account o) {
            int ourPriority = priorities.get(getPriority());
            int theirPriority = priorities.get(o.getPriority());
            return ourPriority - theirPriority;
        }

    /**
     * Retrieves entered account number from user
     * @return entered account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets user's account number entered (corresponds to in getAccountNumber)
     * @param accountNumber the entered account number to be set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static double computeInterest() {
        double totalInterest = 0;
        for (Gasoline gas: gasoline) {
            value += gas.getGallons() * gas.getPrice();
        }
        double streamSum = gasoline.stream().mapToDouble(gas -> gas.getGallons() * gas.getPrice()).sum();
        return value;
    }

}
