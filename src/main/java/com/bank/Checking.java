package com.bank;

public class Checking extends Account{
    public static final int FEE = 5;

    /**
     * Overidden method from Accounts class to return Checking account with fee added
     */
    public void compute() {
        for(int i = 0; i < getPeriods(); i++) {
            double newBalance = (getInterest() * getBalance());
            setBalance(getBalance() + (newBalance / 100) - FEE);
        }
    }

    @Override
    public String toString() {return "Account " + getClass() + "has $" + getBalance() + " after fees added, and " + getPeriods() + " periods."; }

}
