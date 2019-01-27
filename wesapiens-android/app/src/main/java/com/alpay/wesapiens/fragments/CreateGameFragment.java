package com.alpay.wesapiens.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.adapter.FrameListAdapter;
import com.alpay.wesapiens.helper.OnStartDragListener;
import com.alpay.wesapiens.helper.SimpleItemTouchHelperCallback;
import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.models.FrameHelper;

import java.io.FileNotFoundException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateGameFragment extends Fragment implements OnStartDragListener {

    public View view;
    private Unbinder unbinder;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerView recyclerView;
    private FrameListAdapter frameListAdapter;

    public static CreateGameFragment newInstance(){
        return new CreateGameFragment();
    }

    public CreateGameFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.back_button)
    public void backButtonAction(){
        FragmentManager.openFragment((AppCompatActivity) getActivity(), FragmentManager.HOME);
    }

    @OnClick(R.id.add_new_frame_button)
    public void addNewFrame(){
        FrameHelper.addNewFrame();
        frameListAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(FrameHelper.getFrameListSize() - 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_storyboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        try {
            FrameHelper.readFrameList(getContext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frameListAdapter = new FrameListAdapter(getActivity(), this);
        recyclerView = (RecyclerView) view.findViewById(R.id.create_game_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(frameListAdapter);

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(frameListAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FrameHelper.saveFrameList(getContext());
        unbinder.unbind();
    }
}
