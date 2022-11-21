package com.example.smartcity30.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity30.R;
import com.example.smartcity30.bean.commonInterface.AllServiceResult;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragRecyclerViewAdapter extends RecyclerView.Adapter<ServiceFragRecyclerViewAdapter.MyViewHolder> {

    private List<AllServiceResult.RowsBean> allServiceDataList = new ArrayList<>();

    public ServiceFragRecyclerViewAdapter(List<AllServiceResult.RowsBean> allServiceDataList) {
        this.allServiceDataList = allServiceDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_frag_recycler_view_list_form, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(R.string.base_url + allServiceDataList.get(position).getImgUrl())
                .into(holder.iv_service_rv_form_image);
        holder.tv_service_rv_form_name.setText(allServiceDataList.get(position).getServiceName());
    }

    @Override
    public int getItemCount() {
        return allServiceDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_service_rv_form_image;
        TextView tv_service_rv_form_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_service_rv_form_image = itemView.findViewById(R.id.iv_service_rv_form_image);
            tv_service_rv_form_name = itemView.findViewById(R.id.tv_service_rv_form_name);
        }
    }

}
