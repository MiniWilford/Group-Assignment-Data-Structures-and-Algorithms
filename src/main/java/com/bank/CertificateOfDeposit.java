package com.bank;

public class CertificateOfDeposit extends Account{
    private int maturity = 0;

    public int getMaturity() {
        return maturity;
    }

    /**
     * Simple void method to set maturity of user account
     *
     * @param maturity
     * @return length of maturity
     **/
    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }


    /**
     * Override toString method for Certificate of Deposit to return contents of account
     * @return maturity and account balance
     */
    @Override
    public String toString() { return "Account maturity: " + getMaturity() + ". Balance: $" + getBalance(); }

}
