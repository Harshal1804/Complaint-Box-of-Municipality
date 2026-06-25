package com.example.khaddamuktabharat;
public class UserData {
    public String pincode;
    public String emailid;
    public String name;
    public String mobileNo;
    public String address;

    public UserData() {
    }

    public String imageUrl, date, review, Message113;
    String locationgps,newId;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public UserData(String newId, String pincode, String emailid, String name, String mobileNo, String address, String imageUrl, String date, String review, String message, String locationgps) {
       this.newId=newId;
        this.pincode = pincode;
        this.emailid = emailid;
        this.name = name;
        this.mobileNo = mobileNo;
        this.address = address;
        this.imageUrl = imageUrl;
        this.date = date;
        this.review = review;
        this.Message113 = message;
        this.locationgps = locationgps;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMessage() {
        return Message113;
    }

    public void setMessage(String message) {
        this.Message113 = message;
    }

    public String getLocationgps() {
        return locationgps;
    }

    public void setLocationgps(String locationgps) {
        this.locationgps = locationgps;
    }
}