package com.kar.githubsearch.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.githubsearch.R;
import com.kar.githubsearch.data.model.Item;
import com.kar.githubsearch.util.ImageUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mItemList = new ArrayList<>();

    public MainActivityAdapter() {}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_search_user, parent, false);
        return new MainActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MainActivityViewHolder) holder).bind(mItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addNewData(List<Item> itemList) {
        mItemList.clear();
        mItemList.addAll(itemList);
        this.notifyDataSetChanged();
    }

    public void addData(List<Item> itemList) {
        mItemList.addAll(itemList);
        this.notifyDataSetChanged();
    }

    public Item getData(int position) {
        return mItemList.get(position);
    }

    // RecyclerView Click Listener
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class MainActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvUserName;
        private ImageView mIvUser;

        public MainActivityViewHolder(View itemView) {
            super(itemView);

            mTvUserName = itemView.findViewById(R.id.tv_user_name);
            mIvUser = itemView.findViewById(R.id.iv_user);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView, position);
                    }
                }
            });
        }

        public void bind(Item item) {
            mTvUserName.setText(item.getLogin());
            ImageUtility.setImageUrl(mIvUser, item.getAvatarUrl());
        }
    }
}
