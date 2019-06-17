package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.manege.CharManager;
import com.jilian.powerstation.modul.viewmodel.ReportViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.views.CostomMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteOneFargement extends BaseFragment {
    private LineChart lc;
    private TextView tvCenter;
    private TimePickerView pvCustomTime;
    private String sn;
    private ReportViewModel reportViewModel;
    private CostomMarket tMarket;
    private CharManager charManager;
    private long currDate;

    @Override
    protected void loadData() {

    }


    @Override
    protected void createViewModel() {
        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lc = view.findViewById(R.id.lineChart);
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        tMarket = new CostomMarket(getContext(),DisplayUtil.getScreenWidth(getContext()), Utils.convertDpToPixel(350));
        lc.setMarker(tMarket);
        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        // 设置上下左右偏移量
        charManager = new CharManager(lc, tMarket, getContext());
        charManager.setLegend(); // 设置图例
        charManager.setYAxis(); // 设置Y轴
        charManager.setXAxis(); // 设置X轴
        setData();
        sn = getActivity().getIntent().getStringExtra("sn");
        currDate = System.currentTimeMillis();
        getData();
    }

    /**
     *
     */
    public void getData() {
        if (sn == null) return;
        String startTime = DateUtil.getDayBegin(currDate);
        String endTime = DateUtil.getDayEnd(currDate);
        reportViewModel.addReportData(sn, startTime, endTime, 0);
        reportViewModel.getReportData().observe(this, new Observer<BaseDto<ReportListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<ReportListDto> reportListDtoBaseDto) {

            }
        });
    }

    @Override
    protected void initListener() {
        lc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {
            }
        });
        tvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });
    }

    public void setData() {
        LineData lineData = new LineData();
        for (int i = 0; i < 4; i++) {
            List<Entry> yVals1 = new ArrayList<>();
            LineDataSet lineDataSet = null;
            switch (i) {
                case 0:
                    yVals1.add(new Entry(0f, 0f));
                    yVals1.add(new Entry(8f, 6f));
                    yVals1.add(new Entry(13f, 16f));
                    yVals1.add(new Entry(23f, 3f));
                    yVals1.add(new Entry(28f, 11f));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = charManager.setChartData("PV", yVals1, R.color.color_chart_three, R.drawable.bg_color3); // 设置图标数据

                    break;
                case 1:
                    yVals1.add(new Entry(18f, 13f));
                    yVals1.add(new Entry(20f, 16f));
                    yVals1.add(new Entry(23f, 3f));
                    yVals1.add(new Entry(28f, 13f));
                    lineDataSet = charManager.setChartData("Load", yVals1, R.color.color_chart_one, R.drawable.bg_color1); // 设置图标数据
                    break;
                case 2:
                    yVals1.add(new Entry(22f, 13f));
                    yVals1.add(new Entry(26f, 22));
                    yVals1.add(new Entry(28f, 5f));
                    yVals1.add(new Entry(29f, 22));
                    lineDataSet = charManager.setChartData("Grid", yVals1, R.color.color_chart_two, R.drawable.bg_color2); // 设置图标数据
                    break;
                case 3:
                    yVals1.add(new Entry(0f, 0f));
                    yVals1.add(new Entry(3f, 4f));
                    yVals1.add(new Entry(5f, 2f));
                    yVals1.add(new Entry(8f, 5f));
                    yVals1.add(new Entry(10f, 0f));
                    yVals1.add(new Entry(20f, 4f));
                    yVals1.add(new Entry(24f, 3f));
                    yVals1.add(new Entry(26f, 0f));
                    lineDataSet = charManager.setChartData("Battery", yVals1, R.color.color_chart_four, R.drawable.bg_color4); // 设置图标数据
                    break;
            }
            lineData.addDataSet(lineDataSet);
            // 3.将每一组折线数据集添加到折线数据中
            lineData.setDrawValues(false);
            // 4.将折线数据设置给图表
        }
        lc.setData(lineData);
        lc.invalidate();
    }


    /**
     * 初始化时间数据
     */
    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1918, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2029, 01, 01);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvCenter.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, date));
                currDate = date.getTime();
                getData();
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                //各个部分是否显示
                .setType(new boolean[]{true, true, true, false, false, false})
                //时间格式
                .setLabel("", "", "", ":00", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(40, 0, -40, 0, 0, -0)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFe0e0e0)
                .setLineSpacingMultiplier(2f)
                .setSubmitColor(0xFFe0e0e0)
                .setCancelColor(getResources().getColor(R.color.color_text_dark))
                .build();
    }

}