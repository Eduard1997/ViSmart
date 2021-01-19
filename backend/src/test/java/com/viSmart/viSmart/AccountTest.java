package com.viSmart.viSmart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getId() {
        Account new_account = new Account();
        int id = new_account.getId();
        assertEquals(id, new_account.getId());
    }

    @Test
    void getEmail() {
        Account new_account = new Account();
        String test_email = new_account.getEmail();
        assertEquals(test_email, new_account.getEmail());
    }

    @Test
    void getPicture_src() {
        Account new_account = new Account();
        String picture_path = new_account.getPicture_src();
        assertEquals(picture_path ,new_account.getPicture_src());
    }

    @Test
    void getFirst_name() {
        Account new_account = new Account();
        String f_name = new_account.getFirst_name();
        assertEquals(f_name ,new_account.getFirst_name());
    }

    @Test
    void getLast_name() {
        Account new_account = new Account();
        String f_name = new_account.getLast_name();
        assertEquals(f_name ,new_account.getLast_name());
    }

    @Test
    void getRole() {
        Account new_account = new Account();
        int role_nr = new_account.getRole();
        assertEquals(role_nr ,new_account.getRole());
    }

    @Test
    void getPassword() {
        Account new_account = new Account();
        String account_password = new_account.getPassword();
        assertEquals(account_password ,new_account.getPassword());
    }

    @Test
    void getStudy_group() {
        Account new_account = new Account();
        String group_study_name = new_account.getStudy_group();
        assertEquals(group_study_name ,new_account.getStudy_group());
    }

    @Test
    void setId() {
        Account new_account = new Account();
        new_account.setId(2485);
        assertEquals(2485, new_account.getId(), 0.001);
    }
}