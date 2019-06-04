package com.jilian.powerstation.modul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.adapter.ESSListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的电站列表
 */
public class EssListActivity extends BaseActivity {
    private List<ESSDto> mDatas;
    private ESSListAdapter adapter;
    private RecyclerView mRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_ess_list;
    }

    @Override
    public void initView() {
        setNormalTitle("My ESS", v -> finish());
        setrightImageOne(R.drawable.image_scane, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EssListActivity.this, ScanActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
        mRecycle = findViewById(R.id.rv_ess_list);
        iniRecycle();
    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();
        mDatas.add(new ESSDto());
        mDatas.add(new ESSDto());
        adapter = new ESSListAdapter(mDatas, this);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.addItemDecoration(new DividerItemDecoration(this, 1));
        mRecycle.setAdapter(adapter);

        adapter.setItemListener(new OnRecycleItemListener() {
            @Override
            public void OnItemClick(View view, int position) {
                startActivity(new Intent(EssListActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
