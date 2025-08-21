package com.pahanaedu.model;
import java.io.Serializable;
public class User implements Serializable {
    private String username;
    private String passwordHash;
    private String fullName;
    public User() {}
    public User(String username, String passwordHash, String fullName) {
        this.username = username; this.passwordHash = passwordHash; this.fullName = fullName;
    }
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
}
