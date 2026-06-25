package com.example.khaddamuktabharat;

public class MunicipalSignupClass {
    String state,pass,cpass,email,cname,pincode,mobile;

    public MunicipalSignupClass(String state, String pass, String cpass, String email, String cname, String pincode, String mobile) {
        this.state = state;
        this.pass = pass;
        this.cpass = cpass;
        this.email = email;
        this.cname = cname;
        this.pincode = pincode;
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
