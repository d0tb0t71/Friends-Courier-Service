package com.example.fcs;

public class ModelUser {
    String name,email,address,mobile,uid,userStatus;

    public ModelUser() {
    }

    public ModelUser(String name, String email, String address, String mobile, String uid, String userStatus) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.uid = uid;
        this.userStatus = userStatus;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUid() {
        return uid;
    }

    public String getUserStatus() {
        return userStatus;
    }
}
