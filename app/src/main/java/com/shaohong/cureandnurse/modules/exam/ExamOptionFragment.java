package com.shaohong.cureandnurse.modules.exam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaohong.cureandnurse.BaseFragment;
import com.shaohong.cureandnurse.R;
import com.shaohong.cureandnurse.activities.ExamInfoActivity;
import com.shaohong.cureandnurse.bean.Exam;
import com.shaohong.cureandnurse.utils.ContextUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shaohong on 2017/5/2.
 */

public class ExamOptionFragment extends BaseFragment {

    @BindView(R.id.swipeRefreshLayout_exam)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_exam)
    RecyclerView recyclerView;

    public int examType=1;
    boolean isLoading;
    private Handler handler = new Handler();
    private List<Exam> data = new ArrayList<>();
    private ExamRecyclerViewAdapter adapter = new ExamRecyclerViewAdapter(data);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_exam,container,false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        return view;
    }

    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.blueStatus);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        getData();
                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new ExamRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Exam exam=data.get(position);
                Intent intent=new Intent(ContextUtil.getInstance(), ExamInfoActivity.class);
                intent.putExtra("EXAM_INFO",exam);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void initData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);
    }

    private void getData() {
        String tag="";
        switch (examType){
            case 0:
                tag="全院考试";
                break;
            case 1:
                tag="科室考试";
                break;
            case 2:
                tag="专科考试";
                break;
        }
        int size=data.size();
        for (int i=0;i<size+5;i++){
            Exam exam=new Exam();
            exam.setName(tag+"-三基考试"+i);
            exam.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date().getTime()));
            exam.setAddress("第"+i+"会议室");
            exam.setStatus("可报名");
            data.add(exam);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }
}
