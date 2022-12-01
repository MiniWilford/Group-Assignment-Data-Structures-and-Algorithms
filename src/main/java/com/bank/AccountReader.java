package com.bank;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountReader {

    public static void main(String[] args) {
        createAccount();
    }

    /**
     * Create Account(s) using a Try-Catch, reading accounts.json file in directory path
     *
     */
    private static void createAccount() {
        Path accountsFilePath = Paths.get("accounts.json");
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of CreateAccount method
}
