package com.bank;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountReader implements JsonSerializer<Account>, JsonDeserializer<Account> {

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
}
