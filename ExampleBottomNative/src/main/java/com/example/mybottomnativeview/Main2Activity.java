package com.example.mybottomnativeview;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybottomnativeview.adapter.MyAdapter;
import com.example.mybottomnativeview.adapter.baseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Main2Activity";

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        setRecycleView();
        setRecycleView2();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                myAdapter.addData(0, "text1");
                Toast.makeText(this, "text1" + arrayList.size(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.text2:
                arrayList.add("text2");
                Toast.makeText(this, "text2" + arrayList.size(), Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.text3:
                arrayList2.add("text3");
                myAdapter2.notifyDataSetChanged();
                break;

        }
    }

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter<String> myAdapter;
    private List<String> arrayList = new ArrayList<>();

    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager2;
    private MyAdapter<String> myAdapter2;
    private List<String> arrayList2 = new ArrayList<>();

    public void setRecycleView() {
        recyclerView = findViewById(R.id.rl_title);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
       // recyclerView.setHasFixedSize(false);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        // specify an adapter (see also next example)
        myAdapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback(){
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int swiped = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                }else {
                    dragFlags= ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                //第一个参数拖动，第二个删除侧滑
                return makeMovementFlags(dragFlags, swiped);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int oldPosition = viewHolder.getAdapterPosition();
                int newPosition = target.getAdapterPosition();
                if (oldPosition < newPosition) {
                    for (int i = oldPosition; i < newPosition; i++) {
                        // 改变实际的数据集
                        Collections.swap(arrayList, i, i +1);
                    }
                } else {
                    for (int i = oldPosition; i > newPosition; i--) {
                        // 改变实际的数据集
                        Collections.swap(arrayList, i, i - 1);
                    }
                }
                myAdapter.notifyItemMoved(oldPosition, newPosition);
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                arrayList.remove(position);
                myAdapter.notifyItemRemoved(position);
            }
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#303F9F"));
                }
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void setRecycleView2() {
        recyclerView2 = findViewById(R.id.rl_device);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView2.setHasFixedSize(false);
        // use a linear layout manager
        layoutManager2 = new LinearLayoutManager(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                Log.d(TAG, "getMovementFlags() called with: recyclerView = [" + recyclerView + "], viewHolder = [" + viewHolder + "]");
              /*  int swiped = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                //第一个参数拖动，第二个删除侧滑
                return makeMovementFlags(0, swiped);*/
                int swiped = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                int dragFlags = 0;
                //这里为什么要这么写？因为在 线性布局的时候一般是不需要刻意左右拖动
                //所以在这里需要判断一下，是不是网格布局。是的话就可以上下左右一起拖动。
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                //第一个参数拖动，第二个删除侧滑
                return makeMovementFlags(dragFlags, swiped);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                Log.d(TAG, "onMove() called with: recyclerView = [" + recyclerView + "], viewHolder = [" + viewHolder + "], viewHolder1 = [" + viewHolder1 + "]");
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Log.d(TAG, "onSwiped() called with: viewHolder = [" + viewHolder + "], i = [" + i + "]");
                int position = viewHolder.getAdapterPosition();
                arrayList2.remove(position);
                myAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#303F9F"));
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView2);
        recyclerView2.setLayoutManager(layoutManager2);
        // specify an adapter (see also next example)
        myAdapter2 = new MyAdapter(arrayList2);
        recyclerView2.setAdapter(myAdapter2);
    }
}
