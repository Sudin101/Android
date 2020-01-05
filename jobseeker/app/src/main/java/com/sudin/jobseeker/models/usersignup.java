package com.sudin.jobseeker.models;

public class usersignup {
    private String username;
    private  String fullname;
    private String email;
    private  String password;
    private  String publisher;

    public usersignup(String username, String fullname, String email, String password,String publisher) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    @Override
    public String toString() {
        return "usersignup{" +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
