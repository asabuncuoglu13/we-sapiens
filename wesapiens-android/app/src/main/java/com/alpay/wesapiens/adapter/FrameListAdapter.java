package com.alpay.wesapiens.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.helper.ItemTouchHelperAdapter;
import com.alpay.wesapiens.helper.ItemTouchHelperViewHolder;
import com.alpay.wesapiens.helper.OnStartDragListener;
import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.models.FrameHelper;
import com.alpay.wesapiens.utils.Utils;

import java.util.Collections;
import java.util.List;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FrameListAdapter extends RecyclerView.Adapter<FrameListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Frame> mItems;
    private Context mContext;

    private final OnStartDragListener mDragStartListener;

    public FrameListAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
        mItems = FrameHelper.listAll();
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.frameName.setText(mItems.get(position).getFrameName());
        holder.frameImage.setImageDrawable(
                Utils.getDrawableWithName(mContext, mItems.get(position).getFrameStartImage())
        );

        holder.frameImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        public final TextView frameName;
        public final ImageView frameImage;
        public final EditText frameQuestionInput;
        public final ImageButton frameAddNewQuestionButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            frameName = itemView.findViewById(R.id.frame_name);
            frameImage = itemView.findViewById(R.id.frame_image);
            frameQuestionInput = itemView.findViewById(R.id.frame_question_input);
            frameAddNewQuestionButton = itemView.findViewById(R.id.frame_question_button);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
