package com.example.calendar;
// Untuk menyimpan data user
public class Users {
    private String email;
    private String password;

public Users(){

}
    public Users(String username, String password){
        this.email=username;
        this.password=password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

