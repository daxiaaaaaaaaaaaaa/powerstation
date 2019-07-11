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
import com.jilian.powerstation.common.dto.PcsInfoDto;
import com.jilian.powerstation.common.dto.PcsInfoListDto;
import com.jilian.powerstation.listener.CustomItemClickListener;
import com.jilian.powerstation.modul.activity.InverterDetailActivity;
import com.jilian.powerstation.modul.adapter.InverDataAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变器信息
 * 逆变器列表
 */
public class InverterFragment extends BaseFragment implements CustomItemClickListener {
    private List<PcsInfoDto> mDatas;
    private InverDataAdapter adapter;
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
        return R.layout.fragment_inverter;
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
        getPcsInfoList();
    }

    /**
     * 获取逆变器列表
     */
    private void getPcsInfoList() {
        userViewModel.getPcsInfoList(getActivity().getIntent().getStringExtra("sn"));
        userViewModel.getPcsInfoData().observe(this, new Observer<BaseDto<PcsInfoListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PcsInfoListDto> pcsInfoListDtoBaseDto) {
                srHasData.finishRefresh();
                srNoData.finishRefresh();
                if (EmptyUtils.isNotEmpty(pcsInfoListDtoBaseDto.getData())
                        && EmptyUtils.isNotEmpty(pcsInfoListDtoBaseDto.getData().getRows())) {
                    srHasData.setVisibility(View.VISIBLE);
                    srNoData.setVisibility(View.GONE);
                    mDatas.clear();
                    mDatas.addAll(pcsInfoListDtoBaseDto.getData().getRows());
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
                getPcsInfoList();
            }
        });

        srNoData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPcsInfoList();
            }
        });

    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();
        adapter = new InverDataAdapter(mDatas, getContext(), this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        rvList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InverterDetailActivity.class);
        intent.putExtra("data",mDatas.get(position));
        intent.putExtra("sn",getActivity().getIntent().getStringExtra("sn"));
        intent.putExtra("id",mDatas.get(position).getId());
        startActivity(intent);
    }
}
