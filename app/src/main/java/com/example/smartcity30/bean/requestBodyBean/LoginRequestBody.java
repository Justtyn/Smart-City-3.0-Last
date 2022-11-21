package com.example.smartcity30.bean.requestBodyBean;

public class LoginRequestBody {

    /**
     * username : test01
     * password : 123456
     */
    private String username;
    private String password;

    public LoginRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
