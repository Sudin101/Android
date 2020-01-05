package com.sudin.jobseeker.models;

public class users {
    private String _id;
    private String FirstName;
    private String contactnumber;
    private String Address;
    private String email;
    private String image;
    private String dob;
    private String username;
    private String password;
    private String publisher;


    public users(String firstName, String email, String username, String password) {
        FirstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public users(String _id, String firstName, String contactnumber, String address, String email, String image, String dob, String username) {
        this._id = _id;
        FirstName = firstName;
        this.contactnumber = contactnumber;
        Address = address;
        this.email = email;
        this.image = image;
        this.dob = dob;
        this.username = username;
    }

    public users(String firstName, String contactnumber, String email, String image, String username) {
        FirstName = firstName;
        this.contactnumber = contactnumber;
        this.email = email;
        this.image = image;
        this.username = username;
    }

    public users(String firstName, String contactnumber, String address, String email, String image, String dob, String username) {
        FirstName = firstName;
        this.contactnumber = contactnumber;
        Address = address;
        this.email = email;
        this.image = image;
        this.dob = dob;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublisher() {
        return publisher;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "users{" +
                "_id='" + _id + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", contactnumber='" + contactnumber + '\'' +
                ", Address='" + Address + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", dob='" + dob + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
