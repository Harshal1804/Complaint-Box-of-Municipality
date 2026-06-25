package com.example.khaddamuktabharat;

public class CitizenSignupClass {
    // User.java

        private String name;
        private String mobile,password;

        public CitizenSignupClass() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public CitizenSignupClass(String name, String mobile,String password) {
            this.name = name;
            this.mobile = mobile;
            this.password=password;
        }

        public String getName() {
            return name;
        }

        public String getMobile() {
            return mobile;
        }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
