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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ReportDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.manege.CharDateManager;
import com.jilian.powerstation.manege.CharManager;
import com.jilian.powerstation.modul.viewmodel.ReportViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.views.CostomMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteOneFargement extends BaseFragment implements IAxisValueFormatter {
    private LineChart lc;
    private TextView tvCenter;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvTotal1;
    private TextView tvTotal2;
    private TextView tvTotal3;
    private TextView tvTotal4;

    private TimePickerView pvCustomTime;
    private String sn;
    private ReportViewModel reportViewModel;
    private CostomMarket tMarket;
    private CharManager charManager;

    private List<ReportDto> mReportDto;
    private Date currDate;


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
        tvTotal1 = (TextView) view.findViewById(R.id.tv_total_1);
        tvTotal2 = (TextView) view.findViewById(R.id.tv_total_2);
        tvTotal3 = (TextView) view.findViewById(R.id.tv_total_3);
        tvTotal4 = (TextView) view.findViewById(R.id.tv_total_4);
        lc = view.findViewById(R.id.lineChart);
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tMarket = new CostomMarket(getContext(), lc,DisplayUtil.getScreenWidth(getContext()), getContext().getResources().getDimension(R.dimen.widget_size_250), 0);
        lc.setMarker(tMarket);
        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        // 设置上下左右偏移量
        charManager = new CharManager(lc, tMarket, getContext());
        charManager.setLegend(); // 设置图例
        sn = getActivity().getIntent().getStringExtra("sn");
        loadDatas(System.currentTimeMillis());
    }

    private void loadDatas(long currTime) {
        currDate = new Date(currTime);
        tvCenter.setText(DateUtil.dateToString(DateUtil.EPARK_DATE_FORMATER_DATE, currDate));
        getData();
    }


    /**
     *
     */

    public void getData() {
        if (sn == null) return;
        String startTime = DateUtil.getDayBegin(currDate.getTime());
        String endTime = DateUtil.getDayEnd(currDate.getTime());
        reportViewModel.addReportData(sn, startTime, endTime, 0);
        reportViewModel.getReportData().observe(this, new Observer<BaseDto<ReportListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<ReportListDto> reportListDtoBaseDto) {
                if (reportListDtoBaseDto != null && reportListDtoBaseDto.getData() != null) {
                    setData(reportListDtoBaseDto.getData().getRows());
                    setTotalData(reportListDtoBaseDto.getData());
                }
            }


        });
    }

    /**
     * 统计
     *
     * @param dto
     */
    private void setTotalData(ReportListDto dto) {
        tvTotal1.setText(String.valueOf(dto == null || dto.getProduction() == null ? 0 : dto.getProduction()));
        tvTotal2.setText(String.valueOf(dto == null || dto.getRefund() == null ? 0 : dto.getRefund()));
        tvTotal3.setText(String.valueOf(dto == null || dto.getRefund() == null ? 0 : dto.getRefund()));
        tvTotal4.setText(String.valueOf(dto == null || dto.getOffset() == null ? 0 : dto.getOffset()));
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
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getBeforeDay(currDate).getTime());
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getAfterDay(currDate).getTime());
            }
        });
    }

    public void setData(List<ReportDto> rows) {
        LineData lineData = new LineData();
        charManager.removeAll();
        if (rows != null && !rows.isEmpty()) {
            mReportDto = rows;
            charManager.setXAxis(rows.size(), 0, 1, rows.size(), this); // 设置X轴
            List<Entry> yVals1 = new ArrayList<>();
            List<Entry> yVals2 = new ArrayList<>();
            List<Entry> yVals3 = new ArrayList<>();
            List<Entry> yVals4 = new ArrayList<>();
            int maxValue = 50;
            int minValue = 0;
            for (int index = 0, len = rows.size(); index < len; index++) {
                ReportDto dto = rows.get(index);
                int value1 = charManager.getIntValue(dto.getPvProduction());
                int value2 = charManager.getIntValue(dto.getLoadProduction());
                int value3 = charManager.getIntValue(dto.getGridPower());
                int value4 = charManager.getIntValue(dto.getCdPower());

                yVals1.add(new Entry(index + 1, value1));
                yVals2.add(new Entry(index + 1, value2));
                yVals3.add(new Entry(index + 1, value3));
                yVals4.add(new Entry(index + 1, value4));

                maxValue = maxValue > value1 ? maxValue : value1;
                maxValue = maxValue > value2 ? maxValue : value2;
                maxValue = maxValue > value3 ? maxValue : value3;
                maxValue = maxValue > value4 ? maxValue : value4;


                minValue = minValue < value1 ? minValue : value1;
                minValue = minValue < value2 ? minValue : value2;
                minValue = minValue < value3 ? minValue : value3;
                minValue = minValue < value4 ? minValue : value4;

            }
            lineData.addDataSet(charManager.setChartData("PV", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
            lineData.addDataSet(charManager.setChartData("Grid", yVals2, R.color.color_chart_two, R.drawable.bg_color2));
            lineData.addDataSet(charManager.setChartData("Load", yVals3, R.color.color_chart_one, R.drawable.bg_color1));
            lineData.addDataSet(charManager.setChartData("Battery", yVals4, R.color.color_chart_four, R.drawable.bg_color4));
            charManager.setYAxis(maxValue * 2, minValue, (maxValue - minValue) / 10); // 设置Y轴
            charManager.setData(lineData);

        }


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
//                loadDatas(date.getTime());
                setData(null);
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

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return CharDateManager.getDates(mReportDto, value, "HH:mm");
    }
}