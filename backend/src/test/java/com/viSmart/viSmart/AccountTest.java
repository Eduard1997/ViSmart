package com.viSmart.viSmart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void setId() {
        Account new_account = new Account();
        new_account.setId(2485);
        assertEquals(2485, new_account.getId(), 0.001);
    }
}