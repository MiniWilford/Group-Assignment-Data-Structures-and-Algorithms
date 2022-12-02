package com.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class BankerForm {
    private JPanel pnlMain;
    private JPanel jplButtonBar;
    private JPanel pnlCenterMain;
    private JPanel pnlInnerNorth;
    private JPanel pnlInnerCenter;
    private JButton btnSave;
    private JButton btnInterest;
    private JList lstAccounts;
    private JComboBox cmbMakeAccount;
    private JTextField txtBeginBalance;
    private JTextField txtInterest;
    private JTextField txtPeriods;
    private JTextField txtMaturity;
    private JLabel lblMaturity;
    private JButton btnWithdrawal;
    private JButton btnAddAccountsFromJSON;


    private Vector<Account> allAccounts = new Vector<>(); //All accounts to be listed in JList lstAccounts in center Panel

    /**
     * Constructor: On button click event, save contents inside pnlMain and determine if account is CERTIFICATE OF DEPOSIT
     * if account is Certificate of deposit THEN set value of account maturity.
     * else: don't set value of maturity.
     *
     * All entered values return as text, then are converted into each delegated data-types
     */
    public BankerForm() {
        initializeAccountTypeComboBox();
        lstAccounts.setListData(allAccounts); //Fill JList with all accounts in PnlMain
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Conversions of retrieved values from JTextField (String)
                String strbeginningBalance = txtBeginBalance.getText();
                double beginningBalance = Double.parseDouble(strbeginningBalance);

                String strinterestRate = txtInterest.getText();
                double interestRate = Double.parseDouble(strinterestRate);

                String strperiods = txtPeriods.getText();
                int periods = Integer.parseInt(strperiods);

                String type = cmbMakeAccount.getSelectedItem().toString();// Get users selected item
                Account account = Banker.createAccount(type);


                //Populate account's values
                account.setBalance(beginningBalance);
                account.setInterest(interestRate);
                account.setPeriods(periods);

                // Check if Certificate of Deposit is seelected from cmboMakeAccount, if true then set Maturity
                if (cmbMakeAccount.getSelectedItem().toString().equals(Banker.CERTIFICATEOFDEPOSIT)) {
                    if(account instanceof CertificateOfDeposit) {
                        String strMaturity = txtMaturity.getText();
                        int maturity = Integer.parseInt(strMaturity);
                        CertificateOfDeposit certificateOfDeposit = (CertificateOfDeposit) account;
                        ((CertificateOfDeposit) account).setMaturity(maturity);
                    }
                }

                //Add accounts and update UI on add
                allAccounts.add(account);
                lstAccounts.updateUI();
            }
        });

        /**
         * Event listener on button btnInterest (Compute Interest).
         *
         * Computes user entered periods and converts from string data type to integer.
         * Runs through vector stream of all accounts and updates panel after each computation.
         * User experiences real-time changes in the program.
         */
        btnInterest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strGetPeriods = txtPeriods.getText();
                int periods = Integer.parseInt(strGetPeriods);

                allAccounts.stream().forEach(account -> {account.compute();});
                lstAccounts.updateUI();
            }
        });

        /**
         * Action listener on JComboBox to check if Certificate of Deposit is selected
         * If selected then shows extra JTextField
         * Else hides JTextField / is disabled
         */
        cmbMakeAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (cmbMakeAccount.getSelectedItem().toString().equals(Banker.CERTIFICATEOFDEPOSIT)) {
                        lblMaturity.setVisible(true);
                        txtMaturity.setVisible(true);
                        txtMaturity.setEnabled(true);
                    }
                    else {
                        lblMaturity.setVisible(false);
                        txtMaturity.setVisible(false);
                        txtMaturity.setEnabled(false);
                }

            }
        });

        /**
         * Invoked when Withdrawl button is pressed.
         *
         * @param e the event to be processed
         */
        btnWithdrawal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountReader accountsRead = new AccountReader();
                accountsRead.readAccounts();
                lstAccounts.updateUI();
            }
        });

        /**
         * Invoked when 'Add Accounts From File' button is pressed.
         * Then proceeds to read JSON file data from accounts.json
         *
         * @param e the event to be processed
         */
        btnAddAccountsFromJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("accounts.json"));
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(Account.class, new AccountReader());
                    Gson gson = gsonBuilder.create();
                    // Read through Vector of Accounts
                    Vector<Account> inAccounts = gson.fromJson(reader, new TypeToken<Vector<Account>>(){}.getType());
                    allAccounts.addAll(inAccounts);
                    lstAccounts.updateUI();
                    reader.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Add options for JComboBox for account types for user to select
     *
     * Lists 3 options, Savings, Checking, and Certificate of Deposit
     * On initialization, Maturity is disabled unless a selection is made for Certificate of Deposit
     */
    private void initializeAccountTypeComboBox() {
        DefaultComboBoxModel<String> accountTypes = new DefaultComboBoxModel<>();
        accountTypes.addElement(Banker.SAVINGS);
        accountTypes.addElement(Banker.CHECKING);
        accountTypes.addElement(Banker.CERTIFICATEOFDEPOSIT);

        cmbMakeAccount.setModel(accountTypes);

        //Default for Maturity selection is invisible / disabled for user
        lblMaturity.setVisible(false);
        txtMaturity.setVisible(false);
        txtMaturity.setEnabled(false);
    }

    /**
     * Form Main to initilize JPanel and Swing components for user to interact with.*
     *
     * @param args auto-starts main method when java file is executed
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("BankerForm");
        frame.setContentPane(new BankerForm().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
