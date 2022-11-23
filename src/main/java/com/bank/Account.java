package com.bank;

public class Account {

    private double balance;
    private double interest;
    private int periods;


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

}
