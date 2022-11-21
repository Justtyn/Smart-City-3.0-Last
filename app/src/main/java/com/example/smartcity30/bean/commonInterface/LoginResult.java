package com.example.smartcity30.bean.commonInterface;

public class LoginResult {

    /**
     * msg : 操作成功
     * code : 200
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6Ijk2YTNkMTBkLTJkYTktNDU1MS05NDdmLTc3NDgyMTg2OTQ1YSJ9.r7t-Wip7KWKs__yTeZ6sKNJm-mreZTHwrdGgkL3DJ6uJADwGPUgvwrFanv2BimGrqyO0D7YHXpGvnXmgd7_bRQ
     */
    private String msg;
    private int code;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
