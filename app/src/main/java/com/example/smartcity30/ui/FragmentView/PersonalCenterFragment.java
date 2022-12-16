package com.example.smartcity30.ui.FragmentView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity30.LoginActivity;
import com.example.smartcity30.R;

import com.example.smartcity30.RegisterActivity;
import com.example.smartcity30.bean.APIService;
import com.example.smartcity30.bean.commonInterface.UserInfoResult;
import com.example.smartcity30.ui.ActivityView.ChangeThePasswordActivity;
import com.example.smartcity30.ui.ActivityView.FeedbackActivity;
import com.example.smartcity30.ui.ActivityView.ListOfOrdersActivity;
import com.example.smartcity30.ui.ActivityView.MyPointsActivity;
import com.example.smartcity30.ui.ActivityView.MyWalletActivity;
import com.example.smartcity30.ui.ActivityView.PersonalInformationActivity;
import com.example.smartcity30.utils.SPUtils;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalCenterFragment extends Fragment {

    private static final String TAG = "PersonalCenterFragment";
    private String BASE_URL = "http://124.93.196.45:10001";
    private String UserName;
    private View view;
    private ImageView ivPersonalAvatar;
    private TextView tvPersonalUsername;
    public Button btnPersonalInformation;
    public Button btnListOfOrders;
    public Button btnMyWallet;
    public Button btnMyPoints;
    public Button btnFeedback;
    public Button btnChangeThePassword;
    public Button btnSignOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        getIpBySPUtils();
        personalInfoNetworkRequest();
        initView();
        return view;
    }

    private void getIpBySPUtils() {
        BASE_URL = "http://" + SPUtils.getString(requireActivity().getApplicationContext(), SPUtils.IpAddress, "") + ":" + SPUtils.getString(requireActivity().getApplicationContext(), SPUtils.IpPort, "");
    }

    private void personalInfoNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<UserInfoResult> userInfoResultCall = apiService.getUserInfo(SPUtils.getString(requireActivity().getApplicationContext(), SPUtils.TOKEN, ""));
        userInfoResultCall.enqueue(new Callback<UserInfoResult>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResult> call, @NonNull Response<UserInfoResult> response) {
                if (response.isSuccessful()) {
                    UserInfoResult userInfoResult = response.body();
                    if (userInfoResult != null) {
                        UserName = userInfoResult.getUser().getUserName();
                        new Thread(() -> tvPersonalUsername.setText(UserName)).start();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        ivPersonalAvatar = view.findViewById(R.id.iv_personal_avatar);
        tvPersonalUsername = view.findViewById(R.id.tv_personal_username);
        btnPersonalInformation = view.findViewById(R.id.btn_personal_information);
        btnListOfOrders = view.findViewById(R.id.btn_list_of_orders);
        btnMyWallet = view.findViewById(R.id.btn_my_wallet);
        btnMyPoints = view.findViewById(R.id.btn_my_points);
        btnFeedback = view.findViewById(R.id.btn_feedback);
        btnChangeThePassword = view.findViewById(R.id.btn_change_the_password);
        btnSignOut = view.findViewById(R.id.btn_sign_out);

        btnPersonalInformation.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), PersonalInformationActivity.class);
            startActivity(intent);
        });
        btnListOfOrders.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), ListOfOrdersActivity.class);
            startActivity(intent);
        });
        btnMyWallet.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), MyWalletActivity.class);
            startActivity(intent);
        });
        btnMyPoints.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), MyPointsActivity.class);
            startActivity(intent);
        });
        btnFeedback.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), FeedbackActivity.class);
            startActivity(intent);
        });
        btnChangeThePassword.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), ChangeThePasswordActivity.class);
            startActivity(intent);
        });
        btnSignOut.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
            Toast.makeText(requireActivity().getApplicationContext(), "登出成功 请重新登录", Toast.LENGTH_SHORT).show();
        });
    }

}