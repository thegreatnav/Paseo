package com.example.practiceapp1;

public class User {

    String Fullname,Phone,Email,Password;
    public User(String fullname, String phone, String email, String password) {
        Fullname = fullname;
        Phone = phone;
        Email = email;
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
