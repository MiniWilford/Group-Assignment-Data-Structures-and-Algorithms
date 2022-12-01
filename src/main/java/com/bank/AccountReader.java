package com.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AccountReader extends Banker{

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

    // From here down is the Withdrawal

        private static Queue<Account> Withdrawal = new PriorityQueue<>();

        public static void readAccounts() {

            Path AccountFilePath = Paths.get("accounts.txt");
            try {

                List<String> AccountLines = Files.readAllLines(AccountFilePath);
                for (String WithdrawalItem: AccountLines)
                {
                    String[] WithdrawalArray = WithdrawalItem.split(",");
                    if (WithdrawalArray.length >= 4) {
                        CHECKING = WithdrawalArray[0];
                        SAVINGS = WithdrawalArray[1];
                        CERTIFICATEOFDEPOSIT = WithdrawalArray[2];
                        String priority = WithdrawalArray[3];
                        Account account = new Account();
                        Account.setPriority(priority);
                        //This is in account, but I don't know why it's not working
                        Withdrawal.offer(account);
                    }
                }
            } catch (IOException e) {
                // did something go wrong?  We'll end up here.
                throw new RuntimeException(e);
            }
        }

        public static Account fetchNextQualifiedAccount() {
            return Withdrawal.peek();
        }

        public static void removeAccount(Account inAccount) throws Exception {
            Account nextAccount = Withdrawal.poll();
            if (!nextAccount.equals(inAccount)) {
                throw new Exception ("Account is not in queue");
            }
        }

}
