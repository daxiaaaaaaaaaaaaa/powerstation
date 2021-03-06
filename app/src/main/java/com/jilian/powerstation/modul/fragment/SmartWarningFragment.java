package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.DeviceAlarmInfoDto;
import com.jilian.powerstation.common.dto.DeviceAlarmInfoListDto;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.activity.WarningDetailActivity;
import com.jilian.powerstation.modul.adapter.WarningAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.views.RecyclerViewSpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 逆变器警告
 */
public class SmartWarningFragment extends BaseFragment {
    private List<DeviceAlarmInfoDto> mDatas;
    private WarningAdapter adapter;
    private RecyclerView mRecycle;
    private UserViewModel userViewModel;
    private SmartRefreshLayout srNoData;
    private SmartRefreshLayout srHasData;


    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning_inverter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecycle = view.findViewById(R.id.rv_list);
        srNoData = (SmartRefreshLayout) view.findViewById(R.id.sr_no_data);
        srHasData = (SmartRefreshLayout) view.findViewById(R.id.sr_has_data);
        srNoData.setEnableLoadMore(false);
        srHasData.setEnableLoadMore(false);
        iniRecycle();
    }

    @Override
    protected void initData() {
        getDeviceAlarmInfo();
    }

    private void getDeviceAlarmInfo() {
        userViewModel.getDeviceAlarmInfo(getActivity().getIntent().getStringExtra("sn"), 2);
        userViewModel.getDeviceAlarmInfoData().observe(this, new Observer<BaseDto<DeviceAlarmInfoListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<DeviceAlarmInfoListDto> deviceAlarmInfoListDtoBaseDto) {
                srNoData.finishRefresh();
                srHasData.finishRefresh();
                if (deviceAlarmInfoListDtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(deviceAlarmInfoListDtoBaseDto.getData())
                            && EmptyUtils.isNotEmpty(deviceAlarmInfoListDtoBaseDto.getData().getRows())) {
                        mDatas.clear();
                        mDatas.addAll(deviceAlarmInfoListDtoBaseDto.getData().getRows());
                        adapter.notifyDataSetChanged();
                        srNoData.setVisibility(View.GONE);
                        srHasData.setVisibility(View.VISIBLE);
                    } else {
                        srNoData.setVisibility(View.VISIBLE);
                        srHasData.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected void initListener() {
        srNoData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDeviceAlarmInfo();
            }
        });

        srHasData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDeviceAlarmInfo();
            }
        });


    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();
        adapter = new WarningAdapter(mDatas, getContext(),2);
        mRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Integer> map = new HashMap<>();
        map.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, DisplayUtil.dip2px(getContext(), 15));//下间距
        mRecycle.addItemDecoration(new RecyclerViewSpacesItemDecoration(map));
        mRecycle.setAdapter(adapter);

        adapter.setItemListener(new OnRecycleItemListener() {
            @Override
            public void OnItemClick(View view, int position) {
              //  startActivity(new Intent(getContext(), WarningDetailActivity.class));
            }
        });
    }
}
