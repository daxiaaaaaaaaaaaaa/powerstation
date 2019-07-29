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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ReportDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.manege.BaseMarket;
import com.jilian.powerstation.manege.CharBarManage1;
import com.jilian.powerstation.modul.viewmodel.ReportViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.CostomBarMarket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteTwoFargement extends BaseFragment implements IAxisValueFormatter {
    private TextView tvCenter;
    private TextView tvTotal1;
    private TextView tvTotal2;
    private TextView tvTotal3;
    private TextView tvTotal4;
    private TextView tvLeft;
    private TextView tvRight;

    private TimePickerView pvCustomTime;
    private String sn;
    private Date currDate;
    private ReportViewModel reportViewModel;

    private BarChart barChart;
    private CostomBarMarket mBarMarket;
    private CharBarManage1 mCharBarManage;
    private List<ReportDto> mReportDto;

    @Override
    protected void loadData() {
    }

    @Override
    protected void createViewModel() {
        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_two;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvTotal1 = (TextView) view.findViewById(R.id.tv_total_1);
        tvTotal2 = (TextView) view.findViewById(R.id.tv_total_2);
        tvTotal3 = (TextView) view.findViewById(R.id.tv_total_3);
        tvTotal4 = (TextView) view.findViewById(R.id.tv_total_4);
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        barChart = view.findViewById(R.id.lineChart);
        mBarMarket = new CostomBarMarket(getActivity(), barChart);
        mCharBarManage = new CharBarManage1(barChart, mBarMarket);

        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        sn = getActivity().getIntent().getStringExtra("sn");
        loadDatas(System.currentTimeMillis());
    }

    /**
     *
     */
    public void getData() {
        if (sn == null) return;
        String startTime = DateUtil.getDayBegin(currDate.getTime());
        String endTime = DateUtil.getDayEnd(currDate.getTime());
        reportViewModel.addReportData(sn, startTime, endTime, 1);
        reportViewModel.getReportData().observe(this, new Observer<BaseDto<ReportListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<ReportListDto> reportListDtoBaseDto) {
                if (reportListDtoBaseDto != null && reportListDtoBaseDto.getData() != null) {
                    showBarChartMore(reportListDtoBaseDto.getData().getRows());
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
        tvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getBeforeMonth(currDate).getTime());
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getAfterMonth(currDate).getTime());
            }
        });
        mBarMarket.setOnMarketBackCall(new BaseMarket.OnMarketBackCall() {
            @Override
            public String onBackCall(int position) {
                if (Utils.isInBound(mReportDto, position)) {
                    SimpleDateFormat format = new SimpleDateFormat("dd");
                    return format.format(new Date(mReportDto.get(position).getTime()))+"day";
                }
                return "";
            }
        });
    }

    private void loadDatas(long currTime) {
        currDate = new Date(currTime);
        tvRight.setVisibility(DateUtil.isBeforCurrentDate(currDate, 2) ? View.VISIBLE : View.INVISIBLE);
        tvCenter.setText(DateUtil.dateToString(DateUtil.EPARK_DATE_FORMATER_MONTH, currDate));
        getData();
    }

    //显示2条柱状图
    private void showBarChartMore(List<ReportDto> rows) {
        mCharBarManage.removeAll();
        if (Utils.isEmpty(rows)) {
            return;
        }
        List<Float> xAxisValues = new ArrayList<>();
        List<List<Float>> yAxisValues = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<Integer> colours = new ArrayList<>();

        List<Float> yVals1 = new ArrayList<>();
        List<Float> yVals2 = new ArrayList<>();
        float maxValue = 0;
        float minValue = 0;
        mReportDto = rows;
        for (int index = 0, len = rows.size(); index < len; index++) {
            ReportDto dto = rows.get(index);
            xAxisValues.add((float) index);
            float value1 = 0;
            float value2 = 0;
            value1 = mCharBarManage.getFloatValue(dto.getPvProduction());
            value2 = mCharBarManage.getFloatValue(dto.getLoadProduction());
            yVals1.add(value1);
            yVals2.add(value2);
            maxValue = maxValue > value1 ? maxValue : value1;
            maxValue = maxValue > value2 ? maxValue : value2;
            minValue = minValue < value1 ? minValue : value1;
            minValue = minValue < value2 ? minValue : value2;
        }
        yAxisValues.add(yVals1);
        yAxisValues.add(yVals2);
        labels.add("PV1");
        labels.add("PV2");
        colours.add(getActivity().getResources().getColor(R.color.color_chart_four));
        colours.add(getActivity().getResources().getColor(R.color.color_chart_one));
        xAxisValues.add((float) xAxisValues.size());
        mCharBarManage.showMoreBarChart("kwh",xAxisValues, yAxisValues, labels, colours, this)
                .setXAxis(xAxisValues.size(), 0, xAxisValues.size())
                .invalidate()
                .setScalX(mReportDto.size());
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
                loadDatas(date.getTime());
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
        int currItem = (int) value;
        if (currItem >= 0 && currItem < mReportDto.size()) {
            SimpleDateFormat format = new SimpleDateFormat("dd");
            return format.format(new Date(mReportDto.get(currItem).getTime()));
        } else {
            return "";
        }
    }
}