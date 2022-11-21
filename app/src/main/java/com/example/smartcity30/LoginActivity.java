package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity30.bean.APIService;
import com.example.smartcity30.bean.commonInterface.LoginResult;
import com.example.smartcity30.bean.requestBodyBean.LoginRequestBody;
import com.example.smartcity30.utils.SPUtils;
import com.google.gson.Gson;

import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private String BASE_URL = "http://124.93.196.45:10001";
    private EditText etLoginUsername;
    private EditText etLoginPassword;
    private CheckBox ckbLoginSaveInfo;
    private Button btnLoginLogin;
    private Button btnLoginRegister;
    private String TOKEN;
    private String userName;
    private String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        getIpBySPUtils();
        checkUsernameAndPassword();
    }

    private void checkUsernameAndPassword() {
        userName = SPUtils.getString(getApplicationContext(), SPUtils.userName, "");
        passWord = SPUtils.getString(getApplicationContext(), SPUtils.passWord, "");

        etLoginUsername.setText(userName);
        etLoginPassword.setText(passWord);
    }

    private void loginNetWorkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        APIService apiService = retrofit.create(APIService.class);

        LoginRequestBody loginRequestBody = new LoginRequestBody(userName, passWord);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(loginRequestBody));
        Call<LoginResult> loginResultCall = apiService.loginRequest(requestBody);
        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    LoginResult loginResult = response.body();
                    if (loginResult != null) {
                        TOKEN = loginResult.getToken();
                        SPUtils.putString(getApplicationContext(), SPUtils.TOKEN, TOKEN);
                        Log.d(TAG, "onResponse: " + TOKEN);
                        Log.d(TAG, "onResponse: " + loginResult.getMsg());

                        if (Objects.equals(loginResult.getMsg(), "操作成功")) {
                            Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), loginResult.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable throwable) {
                Toast.makeText(getApplicationContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIpBySPUtils() {
        BASE_URL = "http://" + SPUtils.getString(getApplicationContext(), SPUtils.IpAddress, "") + ":" + SPUtils.getString(getApplicationContext(), SPUtils.IpPort, "");
        Log.d(TAG, "getIpBySPUtils: " + BASE_URL);
    }

    private void initView() {
        etLoginUsername = findViewById(R.id.et_login_username);
        etLoginPassword = findViewById(R.id.et_login_password);
        ckbLoginSaveInfo = findViewById(R.id.ckb_login_save_info);
        btnLoginLogin = findViewById(R.id.btn_login_login);
        btnLoginRegister = findViewById(R.id.btn_login_register);

        btnLoginLogin.setOnClickListener(v -> {
            userName = etLoginUsername.getText().toString();
            passWord = etLoginPassword.getText().toString();
            Log.d(TAG, "onClick: " + userName + passWord);

            if (ckbLoginSaveInfo.isChecked()) {
                SPUtils.putString(getApplicationContext(), SPUtils.userName, userName);
                SPUtils.putString(getApplicationContext(), SPUtils.passWord, passWord);
                Log.d(TAG, "onClick: " + "Save Username & Password");
            }

            if (userName != null && passWord != null) {
                Log.d(TAG, "onClick: " + "loginNetWorkRequest");
                loginNetWorkRequest();
            } else {
                if (userName == null && passWord == null) {
                    Toast.makeText(getApplicationContext(), "请输入账号及密码", Toast.LENGTH_SHORT).show();
                }
                if (userName == null && passWord != null) {
                    Toast.makeText(getApplicationContext(), "请输入账号", Toast.LENGTH_SHORT).show();
                }
                if (userName != null && passWord == null) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLoginRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

}