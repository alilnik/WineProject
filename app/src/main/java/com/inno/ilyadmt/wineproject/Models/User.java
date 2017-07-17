package com.inno.ilyadmt.wineproject.Models;

import com.inno.ilyadmt.wineproject.Utility.ROLES;

/**
 * Created by mjazz on 05.07.2017.
 */

public class User {
    private String name, surname;
    private long id;
    private String username;
    private ROLES role;

    public User(String name, String surname, String username, ROLES role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(ROLES role) {
        this.role = role;
    }

    public ROLES getRole() {

        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public String getUsername() {
        return username;
    }



    @Override
    public int hashCode() {
        return 21+username.hashCode()*42;
    }

    @Override
    public boolean equals(Object obj) {
        User s = (User)obj;
        if (obj == null) return false;
        if (!(obj instanceof User)) return false;
        return this.getUsername().equals(s.getUsername());
    }

}
