package com.example.smartcity30.bean;

import com.example.smartcity30.bean.commonInterface.AllServiceResult;
import com.example.smartcity30.bean.commonInterface.LoginResult;
import com.example.smartcity30.bean.commonInterface.RegisterResult;
import com.example.smartcity30.bean.commonInterface.UserInfoResult;

import okhttp3.Authenticator;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    /**
     * 用户登录
     *
     * @POST
     * @Content-Type application/json
     */
    @POST("/prod-api/api/login")
    Call<LoginResult> loginRequest(@Body RequestBody body);

    /**
     * 用户注册
     *
     * @POST
     * @Content-Type application/json
     */
    @POST("/prod-api/api/register")
    Call<RegisterResult> registerRequest(@Body RequestBody body);

    /**
     * 全部服务
     *
     * @GET
     * @Content-Type application/x-www-form-urlencoded
     */
    @FormUrlEncoded
    @GET("/prod-api/api/service/list")
    Call<AllServiceResult> getAllService();

    /**
     * 用户信息
     * 请求头需要 TOKEN 参数
     *
     * @GET
     * @Content-Type application/x-www-form-urlencoded
     */
    @GET("/prod-api/api/common/user/getInfo")
    Call<UserInfoResult> getUserInfo(@Header("Authorization") String TOKEN);
}
