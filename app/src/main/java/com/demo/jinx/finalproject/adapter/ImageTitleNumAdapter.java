package com.demo.jinx.finalproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.bean.NewsBean;
import com.demo.jinx.finalproject.utils.NetUtils;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;
import java.util.Locale;

public class ImageTitleNumAdapter extends BannerAdapter<NewsBean, ImageTitleNumAdapter.BannerViewHolder> {
    public ImageTitleNumAdapter(List<NewsBean> datas) {
        super(datas);
    }

    @Override
    public ImageTitleNumAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindView(ImageTitleNumAdapter.BannerViewHolder holder, NewsBean data, int position, int size) {
        Glide.with(holder.imageView).load(NetUtils.BASE_URL+data.getImg1())
                .into(holder.imageView);
        holder.title.setText(data.getNewsName());
        holder.numIndicator.setText(String.format(Locale.CHINA,"%d/%d",position+1,size));
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView numIndicator;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.bannerTitle);
            numIndicator = itemView.findViewById(R.id.numIndicator);
        }
    }
}
