package com.viSmart.viSmart;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

@Entity
public class User extends Account {

    @JsonIgnore
    private String password;

    public String getPassword(){
        return this.password;
    }
}