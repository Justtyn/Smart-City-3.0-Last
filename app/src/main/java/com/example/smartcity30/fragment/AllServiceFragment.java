package com.example.smartcity30.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.R;
import com.example.smartcity30.adapter.ServiceFragRecyclerViewAdapter;
import com.example.smartcity30.bean.APIService;
import com.example.smartcity30.bean.commonInterface.AllServiceResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllServiceFragment extends Fragment {

    private static final String TAG = "AllServiceFragment";
    private static final String BASE_URL = String.valueOf(R.string.base_url);
    private View view;
    private SearchView svServiceSearch;
    private RecyclerView rvServiceList;
    private List<AllServiceResult.RowsBean> allServiceDataRowBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_service, container, false);
        initView();
        getAllServiceNetworkRequest();
        return view;
    }

    private void getAllServiceNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<AllServiceResult> allServiceResultCall = apiService.getAllService();
        allServiceResultCall.enqueue(new Callback<AllServiceResult>() {
            @Override
            public void onResponse(@NonNull Call<AllServiceResult> call, @NonNull Response<AllServiceResult> response) {
                if (response.isSuccessful()) {
                    AllServiceResult allServiceResult = response.body();
                    if (allServiceResult != null) {
                        allServiceDataRowBeanList = allServiceResult.getRows();
                        initRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllServiceResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        svServiceSearch = view.findViewById(R.id.sv_service_search);
        rvServiceList = view.findViewById(R.id.rv_service_list);

        initSearchView();

    }

    private void initRecyclerView() {
        ServiceFragRecyclerViewAdapter serviceFragRecyclerViewAdapter = new ServiceFragRecyclerViewAdapter(allServiceDataRowBeanList);
        rvServiceList.setLayoutManager();
        rvServiceList.setAdapter(serviceFragRecyclerViewAdapter);
    }

    private void initSearchView() {

        Log.d(TAG, "initSearchView: ");
    }
}