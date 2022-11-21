package com.example.smartcity30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartcity30.adapter.GuidePageBannerAdapter;
import com.example.smartcity30.utils.SPUtils;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuidePageActivity extends AppCompatActivity {

    private static final String TAG = "GuidePageActivity";
    private Banner<Integer, GuidePageBannerAdapter> bannerGuidePage;
    private Button btnGuidePageSkip;
    private Button btnGuidePageEnter;
    private Button btnGuidePageNetSet;
    private final List<Integer> GuidePageImageDataList = new ArrayList<>();
    private Boolean isNetSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);

        initView();
        initBanner();
        checkNetSet();
        checkWeatherIsFirstEnterApp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNetSet();
        Log.d(TAG, "onStart: " + isNetSet);
    }

    public void checkNetSet() {
        String IpAddress = SPUtils.getString(getApplicationContext(), SPUtils.IpAddress, "");
        String IpPort = SPUtils.getString(getApplicationContext(), SPUtils.IpPort, "");
        if (Objects.equals(IpAddress, "") && Objects.equals(IpPort, "")) {
            isNetSet = false;
        } else {
            isNetSet = true;
        }
    }

    private void checkWeatherIsFirstEnterApp() {
        Boolean isFirst = SPUtils.getBoolean(getApplicationContext(), SPUtils.isFirstEnter, true);
        if (isFirst) {
            SPUtils.putBoolean(getApplicationContext(), SPUtils.isFirstEnter, false);
        } else {
            if (isNetSet) {
                Intent intent = new Intent(GuidePageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                SPUtils.putBoolean(getApplicationContext(), SPUtils.isFirstEnter, false);
            }

        }
    }

    private void initBanner() {
        GuidePageImageDataList.add(R.mipmap.guide_page_1);
        GuidePageImageDataList.add(R.mipmap.guide_page_2);
        GuidePageImageDataList.add(R.mipmap.guide_page_3);
        GuidePageImageDataList.add(R.mipmap.guide_page_4);
        GuidePageImageDataList.add(R.mipmap.guide_page_5);

        bannerGuidePage.setAdapter(new GuidePageBannerAdapter(GuidePageImageDataList));
        bannerGuidePage.addBannerLifecycleObserver(this);
        bannerGuidePage.setIndicator(new CircleIndicator(this));
        bannerGuidePage.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        bannerGuidePage.getViewPager2().registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 0) {
                    btnGuidePageSkip.setVisibility(View.VISIBLE);
                } else {
                    btnGuidePageSkip.setVisibility(View.GONE);
                }

                if (position == 4) {
                    btnGuidePageEnter.setVisibility(View.VISIBLE);
                    btnGuidePageNetSet.setVisibility(View.VISIBLE);
                } else {
                    btnGuidePageEnter.setVisibility(View.GONE);
                    btnGuidePageNetSet.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initView() {
        bannerGuidePage = findViewById(R.id.banner_guide_page);
        btnGuidePageSkip = findViewById(R.id.btn_guide_page_skip);
        btnGuidePageEnter = findViewById(R.id.btn_guide_page_enter);
        btnGuidePageNetSet = findViewById(R.id.btn_guide_page_net_set);

        btnGuidePageSkip.setOnClickListener(v -> {
            Intent intent = new Intent(GuidePageActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btnGuidePageEnter.setOnClickListener(v -> {
            if (isNetSet) {
                Intent intent = new Intent(GuidePageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "请先进行网络设置", Toast.LENGTH_SHORT).show();
            }
        });

        btnGuidePageNetSet.setOnClickListener(v -> {
            Intent intent = new Intent(GuidePageActivity.this, NetSetActivity.class);
            startActivity(intent);
        });

    }
}