package com.jilian.powerstation.modul.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.CustomItemClickListener;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.activity.BatteryDetailActivity;
import com.jilian.powerstation.modul.activity.IntelligentDeviceActivity;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.adapter.ConnectedsAdapter;
import com.jilian.powerstation.modul.adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 智能设备列表
 */
public class InverterFragment extends BaseFragment implements CustomItemClickListener {
    private List<ESSDto> mDatas;
    private DataAdapter adapter;
    private RecyclerView mRecycle;

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inverter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecycle = view.findViewById(R.id.rv_list);
        iniRecycle();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();
        mDatas.add(new ESSDto());
        mDatas.add(new ESSDto());
        adapter = new DataAdapter(mDatas, getContext(), 0, this);
        mRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycle.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        mRecycle.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getActivity(), IntelligentDeviceActivity.class));
    }
}
