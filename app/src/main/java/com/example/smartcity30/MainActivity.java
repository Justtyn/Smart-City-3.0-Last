package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartcity30.fragment.AllServiceFragment;
import com.example.smartcity30.fragment.DataAnalysisFragment;
import com.example.smartcity30.fragment.EraParagonFragment;
import com.example.smartcity30.fragment.HomePageFragment;
import com.example.smartcity30.fragment.PersonalCenterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private int curFragmentIndex = 0;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private FragmentContainerView fragment_container_view_main_act;
    private BottomNavigationView bottom_navigation_view_main_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        displayHomeFragmentFirst();
        initBottomNavigation();
    }

    private void displayHomeFragmentFirst() {
        fragmentManager = getSupportFragmentManager();
        fragment = fragmentMap.get(curFragmentIndex);
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new HomePageFragment();
            fragmentMap.put(curFragmentIndex, fragment);
            fragmentTransaction.add(fragment_container_view_main_act.getId(), fragment).setReorderingAllowed(true);
        }
        fragmentTransaction.show(fragment).commit();
    }

    private void initBottomNavigation() {
        bottom_navigation_view_main_act.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main_act_home_page:
                        curFragmentIndex = 0;
                        break;
                    case R.id.navigation_main_act_all_service:
                        curFragmentIndex = 1;
                        break;
                    case R.id.navigation_main_act_era_paragon:
                        curFragmentIndex = 2;
                        break;
                    case R.id.navigation_main_act_data_analysis:
                        curFragmentIndex = 3;
                        break;
                    case R.id.navigation_main_act_personal_center:
                        curFragmentIndex = 4;
                        break;
                }

                fragment = fragmentMap.get(curFragmentIndex);
                fragmentTransaction = fragmentManager.beginTransaction();

                if (fragment == null) {
                    switch (curFragmentIndex) {
                        case 0:
                            fragment = new HomePageFragment();
                            break;
                        case 1:
                            fragment = new AllServiceFragment();
                            break;
                        case 2:
                            fragment = new EraParagonFragment();
                            break;
                        case 3:
                            fragment = new DataAnalysisFragment();
                            break;
                        case 4:
                            fragment = new PersonalCenterFragment();
                            break;

                    }
                    fragmentMap.put(curFragmentIndex, fragment);
                    fragmentTransaction.add(fragment_container_view_main_act.getId(), fragment).setReorderingAllowed(true);
                }

                for (Map.Entry<Integer, Fragment> map : fragmentMap.entrySet()) {
                    fragmentTransaction.hide(map.getValue());
                }

                fragmentTransaction.show(fragment).commit();

                return true;
            }
        });
    }

    private void initView() {
        fragment_container_view_main_act = findViewById(R.id.fragment_container_view_main_act);
        bottom_navigation_view_main_act = findViewById(R.id.bottom_navigation_view_main_act);
    }
}