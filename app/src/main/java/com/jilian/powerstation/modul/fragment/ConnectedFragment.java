package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.adapter.ConnectedsAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 智能设备列表
 */
public class ConnectedFragment extends BaseFragment {
//    private List<ESSDto> mDatas;
//    private ConnectedsAdapter adapter;
//    private RecyclerView mRecycle;
private SmartRefreshLayout srNoData;


    private TextView tvNoData;






    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_connected;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        mRecycle = view.findViewById(R.id.rv_connected);
//        iniRecycle();
        tvNoData = (TextView) view.findViewById(R.id.tv_no_data);
        srNoData = (SmartRefreshLayout) view.findViewById(R.id.sr_no_data);
        srNoData.setVisibility(View.VISIBLE);
        srNoData.setEnableLoadMore(false);
        srNoData.setEnableRefresh(false);
        tvNoData.setText("no data");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    private void iniRecycle() {
      //  mDatas = new ArrayList<>();
//        mDatas.add(new ESSDto());
//        mDatas.add(new ESSDto());
//        adapter = new ConnectedsAdapter(mDatas, getContext());
//        mRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecycle.addItemDecoration(new DividerItemDecoration(getContext(), 1));
//        mRecycle.setAdapter(adapter);
//
//        adapter.setItemListener(new OnRecycleItemListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                startActivity(new Intent(getContext(), MainActivity.class));
//            }
//        });
    }
}
