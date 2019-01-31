package com.alpay.wesapiens.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.helper.ItemTouchHelperAdapter;
import com.alpay.wesapiens.helper.ItemTouchHelperViewHolder;
import com.alpay.wesapiens.helper.OnStartDragListener;
import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.models.FrameHelper;
import com.alpay.wesapiens.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FrameListAdapter extends RecyclerView.Adapter<FrameListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Frame> mItems = new ArrayList<>();
    private AppCompatActivity mAppCompatActivity;
    private String fName;
    private String fStartImage;
    private String fEndImage;
    private String fContext;
    private String fTime;
    private String fPlace;
    private String fQuestion;
    private String fAnswer;

    private final OnStartDragListener mDragStartListener;

    public FrameListAdapter(AppCompatActivity appCompatActivity, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
        mItems.add(new Frame());
        mAppCompatActivity = appCompatActivity;
    }

    public void addNewFrame(){
        mItems.add(new Frame());
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.frameAddStartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.pickImage(mAppCompatActivity);
            }
        });
        holder.frameDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.remove(position);
                notifyItemRemoved(position);
            }
        });
        holder.frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
        holder.frameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName = holder.frameName.getText().toString();
                fQuestion = holder.frameQuestion.getText().toString();
                fAnswer = holder.frameAnswer.getText().toString();
                FrameHelper.addNewFrame(new Frame(1, fName, fTime, fPlace, fStartImage, fEndImage, fContext, fQuestion, fAnswer));
                addNewFrame();
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


        public final RelativeLayout frameLayout;
        public final FloatingActionButton frameDeleteButton;
        public final ImageView frameAddStartImageButton;
        public final ImageView frameAddEndImageButton;
        public final ImageView frameStartImage;
        public final ImageView frameEndImage;
        public final EditText frameName;
        public final EditText frameContext;
        public final EditText frameQuestion;
        public final EditText frameAnswer;
        public final Button frameSave;


        public ItemViewHolder(View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.frame_layout);
            frameDeleteButton = itemView.findViewById(R.id.frame_delete);
            frameAddEndImageButton = itemView.findViewById(R.id.frame_add_start_image);
            frameAddStartImageButton = itemView.findViewById(R.id.frame_add_start_image);
            frameEndImage = itemView.findViewById(R.id.frame_end_image);
            frameStartImage = itemView.findViewById(R.id.frame_start_image);
            frameName = itemView.findViewById(R.id.frame_name);
            frameContext = itemView.findViewById(R.id.frame_context);
            frameQuestion = itemView.findViewById(R.id.frame_question);
            frameAnswer = itemView.findViewById(R.id.frame_answer);
            frameSave = itemView.findViewById(R.id.frame_save);
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
