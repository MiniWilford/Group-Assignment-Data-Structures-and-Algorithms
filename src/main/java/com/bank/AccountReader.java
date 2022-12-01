package com.bank;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountReader implements JsonSerializer<Account>, JsonDeserializer<Account> {

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
        return null;
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
        return null;
    }
}
