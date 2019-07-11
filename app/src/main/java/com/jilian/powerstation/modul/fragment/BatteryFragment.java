package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.BatteryInfoListDto;
import com.jilian.powerstation.common.dto.BatteryfoDto;
import com.jilian.powerstation.listener.CustomItemClickListener;
import com.jilian.powerstation.modul.activity.BatteryDetailActivity;
import com.jilian.powerstation.modul.adapter.BatteryDataAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 电池列表
 */
public class BatteryFragment extends BaseFragment implements CustomItemClickListener {
    private List<BatteryfoDto> mDatas;
    private BatteryDataAdapter adapter;
    private SmartRefreshLayout srHasData;
    private RecyclerView rvList;
    private SmartRefreshLayout srNoData;
    private UserViewModel userViewModel;


    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_battery;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        srHasData = (SmartRefreshLayout) view.findViewById(R.id.sr_has_data);
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        srNoData = (SmartRefreshLayout) view.findViewById(R.id.sr_no_data);
        srHasData.setEnableLoadMore(false);
        srNoData.setEnableLoadMore(false);
        iniRecycle();
    }

    @Override
    protected void initData() {
        getBatteryInfoList();
    }
    /**
     * 获取电池列表
     */
    private void getBatteryInfoList() {
        userViewModel.getBatteryInfoList(getActivity().getIntent().getStringExtra("sn"));
        userViewModel.getBatteryInfoData().observe(this, new Observer<BaseDto<BatteryInfoListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BatteryInfoListDto> batteryInfoListDtoBaseDto) {
                srHasData.finishRefresh();
                srNoData.finishRefresh();
                if (EmptyUtils.isNotEmpty(batteryInfoListDtoBaseDto.getData())
                        && EmptyUtils.isNotEmpty(batteryInfoListDtoBaseDto.getData().getRows())) {
                    srHasData.setVisibility(View.VISIBLE);
                    srNoData.setVisibility(View.GONE);
                    mDatas.clear();
                    mDatas.addAll(batteryInfoListDtoBaseDto.getData().getRows());
                    adapter.notifyDataSetChanged();
                } else {
                    srHasData.setVisibility(View.GONE);
                    srNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    protected void initListener() {
        srHasData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBatteryInfoList();
            }
        });

        srNoData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBatteryInfoList();
            }
        });

    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();
        adapter = new BatteryDataAdapter(mDatas, getContext(),this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(),1));
        rvList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), BatteryDetailActivity.class);
        intent.putExtra("data",mDatas.get(position));
        intent.putExtra("sn",getActivity().getIntent().getStringExtra("sn"));
        intent.putExtra("id",mDatas.get(position).getId());
        startActivity(intent);
    }
}
