package com.viSmart.viSmart;

import javax.persistence.Entity;

@Entity
public class User extends Account {

    public String getPassword(){
        return this.password;
    }
}