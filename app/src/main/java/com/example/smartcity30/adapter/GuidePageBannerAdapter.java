package com.example.smartcity30.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class GuidePageBannerAdapter extends BannerAdapter<Integer, GuidePageBannerAdapter.MyImageViewHolder> {

    public GuidePageBannerAdapter(List<Integer> data) {
        super(data);
    }

    @Override
    public MyImageViewHolder onCreateHolder(ViewGroup viewGroup, int i) {
        ImageView mImageView = (ImageView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_view_guide_page_form, viewGroup, false);
        return new MyImageViewHolder(mImageView);
    }

    @Override
    public void onBindView(MyImageViewHolder myImageViewHolder, Integer integer, int position, int size) {
        myImageViewHolder.ivGuidePageForm.setImageResource(mDatas.get(position));
    }

    static class MyImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGuidePageForm;

        public MyImageViewHolder(@NonNull View itemView) {
            super(itemView);

            ivGuidePageForm = itemView.findViewById(R.id.iv_guide_page_form);
        }
    }
}
