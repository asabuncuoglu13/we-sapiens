package com.alpay.wesapiens.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuViewHolder> {
    private List<MenuItem> mDataset;

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;
        public MenuViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.menu_text);
            mImageView = itemView.findViewById(R.id.menu_icon);
        }
    }

    public MenuListAdapter(ArrayList<MenuItem> menuItems) {
        mDataset = menuItems;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_holder, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.mImageView.setImageDrawable(mDataset.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
