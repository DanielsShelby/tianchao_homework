package com.example.exam_three.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exam_three.bean.UserBean;
import com.example.exam_three.databinding.ViewItemListBinding;
import com.example.exam_three.util.DbUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ListRlvAdapter extends RecyclerView.Adapter<ListRlvAdapter.ViewHolder> {
    private Context context;
    public List<UserBean> list = new ArrayList<>();

    public ListRlvAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<UserBean> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void deleteItem(int i) {
        DbUtil.getDbUtil().deleteItem(list.get(i));
        list.remove(i);
        this.notifyDataSetChanged();
    }

    public void updateItem(int i, UserBean bean) {
        DbUtil.getDbUtil().update(bean);
        list.set(i, bean);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewItemListBinding inflate = ViewItemListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserBean bean = list.get(position);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(bean.getImgpath())));
            holder.mImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        holder.mTextView.setText(bean.getName());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnClick.onLongClickListener(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(ViewItemListBinding itemView) {
            super(itemView.getRoot());
            mImageView = itemView.ImageView;
            mTextView = itemView.TextView;
        }
    }

    private OnClick mOnClick;

    public void setOnClick(OnClick onClick) {
        mOnClick = onClick;
    }

    public interface OnClick {
        void onLongClickListener(int i);
    }
}
