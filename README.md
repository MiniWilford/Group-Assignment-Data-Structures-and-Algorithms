# Final Group Assignment, IT 2045C  

### Our Super Important, Extremely Practical, New Fangled Banking System  

###### By: Magnus Knudsen, Kyle Dejarnett, and Josh Miller  

## Purpose of the Code:  

##### Create a Java Program that:  
  
* Reads the account information from an included .json file and creates those accounts
* Allows the user to create additional accounts from 3 categories
    * Checking
    * Savings
    * Certificate of Deposit
* Allows the user to give each account a unique account number
  * Prevents the user from creating duplicate accounts or accounts with duplicate account numbers
* Computes the in interest of all accounts earned over a certain time with specified interest rate  
* Allows the User to withdraw money
  * Automatically chooses to withdraw from the account with the lowest interest rate

## Class Diagram:

![Banker Class Diagram](https://github.com/MiniWilford/Group-Assignment-Data-Structures-and-Algorithms/blob/main/BankerClassDiagram.drawio.png)

## Self Grade Sheet:

* Have a GitHub repository, with commits from all teammates
* **This repository should have a README.md describes your project. 10/10**
* **Create a class diagram in draw.io and include it in your README.md 10/10**
* Start with the program that collects accounts.
* **Initialize your accounts by reading the provided JSON file, accounts.json Download accounts.json.  Allow the user to create additional accounts with the GUI. 8/10**
* **Do not allow the user to create duplicate accounts, or duplicate account numbers. 5/10**
  * Add an account number attribute to class Account, and a corresponding field to the GUI.
  * Place all used account numbers in a Set.
  * When the user enters an account, validate that it is not currently in the Set of account numbers.
* Allow the user to withdraw money from accounts.  Start by withdrawing from the account that pays the lowest interest
  * **Adjust the GUI to allow withrdawls.  10/10**
  * Place accounts in a priority queue, based on rate.
  * **Use Comparator/compareTo to compare rates.  8/10**
  * Allow partial withdraws from accounts; simply update principle
* **Report total interest earned, across all accounts, for a given period. 7/10**
  * Iterate over the collection.  Compute interest earned.  Store interest earned from each account in a collection (ArrayList, etc.)  Iterate and sum.
* **Test your work. 8/10**
  * Write unit tests to cover the cases above.
  * Ensure they run with GitHub actions.  
* **Code is in good form, and conforms to best practices.  Classes and public methods contain JavaDoc. 10/10**
* **Do something extra, beyond the minimum requirements stated here.  10/10**
  * What did you do?  Must list to earn credit.
  * Added image icons to Save / Compute buttons as well as added tool tips to buttons for ease of use.
