package com.example.calendar;
// Untuk menyimpan data user
public class Users {
    private String email;
    private String password;

    public Users(String username, String password){
        this.email=email;
        this.password=password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

