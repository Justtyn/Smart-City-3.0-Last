package com.example.smartcity30.ui.FragmentView;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity30.LoginActivity;
import com.example.smartcity30.R;

import com.example.smartcity30.ui.ActivityView.ChangeThePasswordActivity;
import com.example.smartcity30.ui.ActivityView.FeedbackActivity;
import com.example.smartcity30.ui.ActivityView.ListOfOrdersActivity;
import com.example.smartcity30.ui.ActivityView.MyPointsActivity;
import com.example.smartcity30.ui.ActivityView.MyWalletActivity;
import com.example.smartcity30.ui.ActivityView.PersonalInformationActivity;

import java.util.Objects;

public class PersonalCenterFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView ivPersonalAvatar;
    private TextView tvPersonalUsername;
    private Button btnPersonalInformation;
    private Button btnListOfOrders;
    private Button btnMyWallet;
    private Button btnMyPoints;
    private Button btnFeedback;
    private Button btnChangeThePassword;
    private Button btnSignOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        initView();
        return view;
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

        btnPersonalInformation.setOnClickListener(this);
        btnListOfOrders.setOnClickListener(this);
        btnMyWallet.setOnClickListener(this);
        btnMyPoints.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
        btnChangeThePassword.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        btnPersonalInformation.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireActivity(), PersonalInformationActivity.class);
            startActivity(intent);
        });
        btnListOfOrders.setOnClickListener(view2 -> {
            Intent intent = new Intent(requireActivity(), ListOfOrdersActivity.class);
            startActivity(intent);
        });
        btnMyWallet.setOnClickListener(view3 -> {
            Intent intent = new Intent(requireActivity(), MyWalletActivity.class);
            startActivity(intent);
        });
        btnMyPoints.setOnClickListener(view4 -> {
            Intent intent = new Intent(requireActivity(), MyPointsActivity.class);
            startActivity(intent);
        });
        btnFeedback.setOnClickListener(view5 -> {
            Intent intent = new Intent(requireActivity(), FeedbackActivity.class);
            startActivity(intent);
        });
        btnChangeThePassword.setOnClickListener(view6 -> {
            Intent intent = new Intent(requireActivity(), ChangeThePasswordActivity.class);
            startActivity(intent);
        });
        btnSignOut.setOnClickListener(view7 -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}