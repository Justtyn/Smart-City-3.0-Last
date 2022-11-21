package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity30.bean.APIService;
import com.example.smartcity30.bean.commonInterface.RegisterResult;
import com.example.smartcity30.bean.requestBodyBean.RegisterRequestBody;
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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private String BASE_URL = "http://124.93.196.45:10001";
    private TextView tvRegisterBack;
    private EditText etRegisterUsername;
    private EditText etRegisterPassword;
    private EditText etRegisterPhoneNumber;
    private RadioGroup rgRegisterSex;
    private RadioButton rbRegisterMan0;
    private RadioButton rbRegisterWoman1;
    private Button btnRegisterSubmit;
    private String userName, passWord, phoneNumber, sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getIpBySPUtils();
        initView();
    }

    private void getIpBySPUtils() {
        String IpAddress = SPUtils.getString(getApplicationContext(), SPUtils.IpAddress, "");
        String IpPort = SPUtils.getString(getApplicationContext(), SPUtils.IpPort, "");
        BASE_URL = "http://" + IpAddress + ":" + IpPort;
        Log.d(TAG, "getIpBySPUtils: " + BASE_URL);
    }

    private void initView() {
        tvRegisterBack = findViewById(R.id.tv_register_back);
        etRegisterUsername = findViewById(R.id.et_register_username);
        etRegisterPassword = findViewById(R.id.et_register_password);
        etRegisterPhoneNumber = findViewById(R.id.et_register_phone_number);
        rgRegisterSex = findViewById(R.id.rg_register_sex);
        rbRegisterMan0 = findViewById(R.id.rb_register_man_0);
        rbRegisterWoman1 = findViewById(R.id.rb_register_woman_1);
        btnRegisterSubmit = findViewById(R.id.btn_register_submit);

        onClick();

    }

    private void onClick() {
        rgRegisterSex.clearCheck();

        tvRegisterBack.setOnClickListener(v -> finish());

        btnRegisterSubmit.setOnClickListener(v -> {
            userName = etRegisterUsername.getText().toString();
            passWord = etRegisterPassword.getText().toString();
            phoneNumber = etRegisterPhoneNumber.getText().toString();

            registerNetworkRequest();
        });

        rbRegisterMan0.setOnClickListener(v -> sex = "0");
        rbRegisterWoman1.setOnClickListener(v -> sex = "1");
    }

    private void registerNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        RegisterRequestBody registerRequestBody = new RegisterRequestBody(userName, passWord, phoneNumber, sex);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(registerRequestBody));

        APIService apiService = retrofit.create(APIService.class);
        Call<RegisterResult> registerResultCall = apiService.registerRequest(requestBody);

        registerResultCall.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResult> call, @NonNull Response<RegisterResult> response) {
                if (response.isSuccessful()) {
                    RegisterResult registerResult = response.body();
                    if (registerResult != null) {
                        if (Objects.equals(registerResult.getMsg(), "操作成功")) {
                            Toast.makeText(getApplicationContext(), "注册成功, 请登录", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), registerResult.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResult> call, @NonNull Throwable throwable) {
                Toast.makeText(getApplicationContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });


    }
}