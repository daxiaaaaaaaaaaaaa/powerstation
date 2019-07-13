package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseEventMsg;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.event.RxBus;
import com.jilian.powerstation.listener.CustomItemClickListener;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.modul.adapter.ESSListAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的电站列表
 */
public class EssListActivity extends BaseActivity implements CustomItemClickListener {
    private List<PowerDto> mDatas;
    private ESSListAdapter adapter;
    private RecyclerView mRecycle;
    private UserViewModel viewModel;
    private SmartRefreshLayout srHasData;
    private SmartRefreshLayout srNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.clearSpecifyActivities(new Class[]{WelcomeActivity.class});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }

    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
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
                startActivity(intent);
            }
        });
        mRecycle = findViewById(R.id.rv_ess_list);
        srHasData = (SmartRefreshLayout) findViewById(R.id.sr_has_data);
        srNoData = (SmartRefreshLayout) findViewById(R.id.sr_no_data);
        srNoData.setEnableLoadMore(false);
        iniRecycle();
    }

    private void iniRecycle() {
        mDatas = new ArrayList<>();

        adapter = new ESSListAdapter(mDatas, this, this);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.addItemDecoration(new DividerItemDecoration(this, 1));
        mRecycle.setAdapter(adapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        getPowerList();
    }

    @Override
    public void initListener() {
        RxBus.getInstance().toObservable().map(
                new io.reactivex.functions.Function<Object, BaseEventMsg>() {
                    @Override
                    public BaseEventMsg apply(Object o) throws Exception {
                        return (BaseEventMsg) o;
                    }
                }).subscribe(new io.reactivex.functions.Consumer<BaseEventMsg>() {
            @Override
            public void accept(BaseEventMsg o) throws Exception {
                if (o != null && o.getAddPowersEvent() != null) {
                    if(o.getAddPowersEvent().getCode()==200){
                        getPowerList();
                    }
                }

            }
        });
        srHasData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getPowerList();
            }
        });
        srHasData.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPowerList();
            }
        });
        srNoData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getPowerList();
            }
        });
    }

    private Integer pageNum = 1;//
    private Integer pageSize = 20;//

    /**
     * 我的电站列表
     */
    private void getPowerList() {
        viewModel.getPowerList(pageNum, pageSize);
        viewModel.getPowerListliveData().observe(this, new Observer<BaseDto<PowerListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerListDto> listBaseDto) {
                hideLoadingDialog();
                srNoData.finishRefresh();
                srHasData.finishRefresh();
                srHasData.finishLoadMore();
                //有数据
                if (listBaseDto.isSuccess() && EmptyUtils.isNotEmpty(listBaseDto.getData().getRows())) {
                    srNoData.setVisibility(View.GONE);
                    srHasData.setVisibility(View.VISIBLE);
                    if (pageNum == 1) {
                        mDatas.clear();
                    }
                    mDatas.addAll(listBaseDto.getData().getRows());
                    adapter.notifyDataSetChanged();
                } else {
                    //说明是上拉加载
                    if (pageNum > 1) {
                        pageNum--;
                    } else {
                        //第一次就没数据
                        srNoData.setVisibility(View.VISIBLE);
                        srHasData.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(EssListActivity.this, MainActivity.class);
        intent.putExtra("sn",mDatas.get(position).getSn());
        intent.putExtra("name",mDatas.get(position).getProductName());
        intent.putExtra("data",mDatas.get(position));
        MyApplication.getInstance().setPowerDto(mDatas.get(position));
        startActivity(intent);
        finish();
    }
}
