package com.emumu.spice.entity;

import java.io.Serializable;

public class VerifyInfo implements Serializable {
    private String id;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
