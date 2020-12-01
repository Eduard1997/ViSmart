package com.viSmart.viSmart;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String name) {
        super("Could not find account " + name);
    }
}
