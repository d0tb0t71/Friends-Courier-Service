package com.example.fcs;

public class ModelParcel {

    String address,date,mobile,name,productType,addedBy,status,sender;

    public ModelParcel() {
    }

    public ModelParcel(String address, String date, String mobile, String name, String productType, String addedBy,String status,String sender) {
        this.address = address;
        this.date = date;
        this.mobile = mobile;
        this.name = name;
        this.productType = productType;
        this.addedBy = addedBy;
        this.status = status;
        this.sender=sender;
    }


    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getProductType() {
        return productType;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getStatus() {
        return status;
    }

    public String getSender() {
        return sender;
    }
}
