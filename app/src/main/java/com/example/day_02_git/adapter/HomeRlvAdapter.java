package com.example.day_02_git.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day_02_git.bean.InfoDataBean;
import com.example.day_02_git.databinding.ViewInfoItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeRlvAdapter extends RecyclerView.Adapter<HomeRlvAdapter.ViewHolder> {
    Context context;
    List<InfoDataBean.DataBean.DatasBean> list = new ArrayList<>();

    public HomeRlvAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<InfoDataBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewInfoItemBinding inflate = ViewInfoItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoDataBean.DataBean.DatasBean bean = list.get(position);
        Glide.with(context).load(bean.getEnvelopePic()).into(holder.mImageView);
        holder.mTextView.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;
        private final ImageView mImageView;

        public ViewHolder(ViewInfoItemBinding itemView) {
            super(itemView.getRoot());
            mTextView = itemView.TextView;
            mImageView = itemView.ImageView;
        }
    }
}
