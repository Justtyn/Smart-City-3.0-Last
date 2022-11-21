package com.example.smartcity30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity30.utils.SPUtils;

public class NetSetActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NetSetActivity";
    private TextView tv_net_set_back;
    private EditText etNetSetIpAddress;
    private EditText etNetSetIpPort;
    private Button btnNetSetSave;
    private String IpAddress;
    private String IpPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_set);
        initView();
    }


    private void initView() {
        tv_net_set_back = findViewById(R.id.tv_net_set_back);
        etNetSetIpAddress = findViewById(R.id.et_net_set_ip_address);
        etNetSetIpPort = findViewById(R.id.et_net_set_ip_port);
        btnNetSetSave = findViewById(R.id.btn_net_set_save);

        tv_net_set_back.setOnClickListener(this);
        btnNetSetSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        btnNetSetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNetSetIpAddress.getText() != null && etNetSetIpPort.getText() != null) {
                    IpAddress = String.valueOf(etNetSetIpAddress.getText());
                    IpPort = String.valueOf(etNetSetIpPort.getText());
                    Log.d(TAG, "onClick: " + IpAddress + IpPort);

                    SPUtils.putString(getApplicationContext(), SPUtils.IpAddress, IpAddress);
                    SPUtils.putString(getApplicationContext(), SPUtils.IpPort, IpPort);

                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "请输入IP地址及端口号", Toast.LENGTH_SHORT).show();
                    if (etNetSetIpAddress.getText() == null && etNetSetIpPort.getText() != null) {
                        Toast.makeText(getApplicationContext(), "请输入IP地址", Toast.LENGTH_SHORT).show();
                    }
                    if (etNetSetIpAddress.getText() != null && etNetSetIpPort.getText() == null) {
                        Toast.makeText(getApplicationContext(), "请输入端口号", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tv_net_set_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}