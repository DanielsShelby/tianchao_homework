package com.example.exam_two.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.exam_two.bean.InfoListBean;
import com.example.exam_two.databinding.ViewItemLeftBinding;
import com.example.exam_two.databinding.ViewItemRightBinding;
import com.example.exam_two.util.MyReceiver;

import java.util.ArrayList;
import java.util.List;

public class HomeRlvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<InfoListBean.DataBean.DatasBean> list = new ArrayList();
    private final MyReceiver mReceiver;

    public HomeRlvAdapter(Context context) {
        this.context = context;
        mReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.super.boy");
        context.registerReceiver(mReceiver, filter);
    }

    public void unRegisterReceiver() {
        context.unregisterReceiver(mReceiver);
    }

    public void setList(List<InfoListBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                ViewItemLeftBinding left = ViewItemLeftBinding.inflate(LayoutInflater.from(context), parent, false);
                return new ViewHolder(left);
            case 2:
                ViewItemRightBinding right = ViewItemRightBinding.inflate(LayoutInflater.from(context), parent, false);
                return new ViewHolderTo(right);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final InfoListBean.DataBean.DatasBean bean = list.get(position);
        switch (getItemViewType(position)) {
            case 1:
                ViewHolder holder1 = (ViewHolder) holder;
                Glide.with(context).load(bean.getEnvelopePic()).into(holder1.mImageView);
                holder1.mTextView.setText(bean.getTitle());
                break;
            case 2:
                ViewHolderTo holderTo = (ViewHolderTo) holder;
                Glide.with(context).load(bean.getEnvelopePic()).into(holderTo.mImageView);
                holderTo.mTextView.setText(bean.getTitle());
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.super.boy");
                intent.putExtra("title", bean.getTitle());
                context.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 1;
        }
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(ViewItemLeftBinding itemView) {
            super(itemView.getRoot());
            mImageView = itemView.ImageView;
            mTextView = itemView.TextView;
        }
    }

    public class ViewHolderTo extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolderTo(ViewItemRightBinding itemView) {
            super(itemView.getRoot());
            mImageView = itemView.ImageView;
            mTextView = itemView.TextView;
        }
    }
}
