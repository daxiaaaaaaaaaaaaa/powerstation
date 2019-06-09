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
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.adapter.DataAdapter;
import com.jilian.powerstation.modul.adapter.WarningAdapter;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.views.RecyclerViewSpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 智能设备列表
 */
public class BatteryWarningFragment extends BaseFragment {
    private List<ESSDto> mDatas;
    private WarningAdapter adapter;
    private RecyclerView mRecycle;

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning_battery;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecycle = view.findViewById(R.id.battery_list);
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
        adapter = new WarningAdapter(mDatas, getContext());
        mRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        HashMap<String, Integer> map = new HashMap<>();
        map.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, DisplayUtil.dip2px(getContext(), 15));//下间距
        mRecycle.addItemDecoration(new RecyclerViewSpacesItemDecoration(map));

        mRecycle.setAdapter(adapter);

        adapter.setItemListener(new OnRecycleItemListener() {
            @Override
            public void OnItemClick(View view, int position) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }
}
