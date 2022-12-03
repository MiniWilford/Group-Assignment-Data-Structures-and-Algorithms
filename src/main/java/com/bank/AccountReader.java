package com.bank;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AccountReader extends Account implements JsonSerializer<Account>, JsonDeserializer<Account>{


    /**
     * Reads if all accounts read are unique
     */
    private static Map<String, Account> allAccounts = new HashMap<>();


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

    /**
     *
     * @param account
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(account.getClass().getSimpleName()));
        result.add("properties", jsonSerializationContext.serialize(account, account.getClass()));
        return result;
    }

    /**
     *
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return
     * @throws JsonParseException
     */
    @Override
    public Account deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String accountType = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return jsonDeserializationContext.deserialize(element, Class.forName(accountType));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    // By Josh Miller
    /**
     * Creates and stores account queue under 'withdrawalQueue' under a priority-queue
     */
    private static Queue<Account> withdrawalQueue = new PriorityQueue<>();

    /**
     * readAccounts looks through the accounts.json
     * and goes through all lines to determine accounts new priority amongst an array.
     */
    public void readAccounts() {
        Path accountFilePath = Paths.get("accounts.json");
        try {

            List<String> accountLines = Files.readAllLines(accountFilePath);
            for (String withdrawalItem: accountLines)
            {
                String[] withdrawalArray = withdrawalItem.split(",");
                if (withdrawalArray.length >= 4) {
                    CHECKING = withdrawalArray[0];
                    SAVINGS = withdrawalArray[1];
                    CERTIFICATEOFDEPOSIT = withdrawalArray[2];
                    String priority = withdrawalArray[3];
                    Account account = new Account();
                    account.setPriority(priority);
                    //This is in account, but I don't know why it's not working
                    withdrawalQueue.offer(account);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Retrieves head of account priority queue
     * @return next valid account at head of the priority queue
     */
    public static Account fetchNextQualifiedAccount() {
        return withdrawalQueue.peek();
    }

    /**
     *  Removes account at head of queue if doesn't meet expected priority match
     * @param inAccount
     * @throws Exception
     */
    public static void removeAccount(Account inAccount) throws Exception {
        Account nextAccount = withdrawalQueue.poll();
        if (!nextAccount.equals(inAccount)) {
            throw new Exception ("Account is not in queue");
        }
    }
}
