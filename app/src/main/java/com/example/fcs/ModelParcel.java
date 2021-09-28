package com.example.fcs;

public class ModelParcel {

    String address,date,mobile,name,productType,addedBy,status,sender,arrivedRW,arrivedW,shipped,delivered,date_time;

    public ModelParcel() {
    }


    public ModelParcel(String address, String date, String mobile, String name, String productType, String addedBy, String status, String sender, String arrivedRW, String arrivedW, String shipped, String delivered,String date_time) {
        this.address = address;
        this.date = date;
        this.mobile = mobile;
        this.name = name;
        this.productType = productType;
        this.addedBy = addedBy;
        this.status = status;
        this.sender = sender;
        this.arrivedRW = arrivedRW;
        this.arrivedW = arrivedW;
        this.shipped = shipped;
        this.delivered = delivered;
        this.date_time = date_time;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getArrivedRW() {
        return arrivedRW;
    }

    public void setArrivedRW(String arrivedRW) {
        this.arrivedRW = arrivedRW;
    }

    public String getArrivedW() {
        return arrivedW;
    }

    public void setArrivedW(String arrivedW) {
        this.arrivedW = arrivedW;
    }

    public String getShipped() {
        return shipped;
    }

    public void setShipped(String shipped) {
        this.shipped = shipped;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }
}
