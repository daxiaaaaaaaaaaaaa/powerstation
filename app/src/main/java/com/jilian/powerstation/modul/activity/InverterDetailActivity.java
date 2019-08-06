package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.contrarywind.view.WheelView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.PcsHistoryDataDto;
import com.jilian.powerstation.common.dto.PcsHistoryDataListDto;
import com.jilian.powerstation.common.dto.PcsInfoDetailDto;
import com.jilian.powerstation.common.dto.PcsInfoDto;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.manege.BaseMarket;
import com.jilian.powerstation.manege.CharBarManage1;
import com.jilian.powerstation.manege.CharDateManager;
import com.jilian.powerstation.manege.CharManager;
import com.jilian.powerstation.modul.adapter.WheelAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.CostomBarMarket;
import com.jilian.powerstation.views.CostomMarket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 逆变器详情
 */
public class InverterDetailActivity extends BaseActivity implements IAxisValueFormatter {
    private LineChart lineChart;
    private BarChart barChart;
    private UserViewModel userViewModel;
    private CharManager mCharManager;
    private CharBarManage1 mCharBarManage;
    private CostomMarket mLineMarket;
    private CostomBarMarket mBarMarket;
    private RadioGroup dateTitle;

    ArrayList<String> mTitle = new ArrayList<>();

    private TextView detailVoltage1;//PV1输入电压
    private TextView detailCurrent1;//PV1输入电流
    private TextView detailPower1;//PV1输入功率
    private TextView detailYield1;//PV1发电量

    private TextView detailVoltage2;//PV2输入电压
    private TextView detailCurrent2;//PV2输入电流
    private TextView detailPower2;//PV2输入功率
    private TextView detailYield2;//PV2发电量

    private TextView detailFeed;//电网馈电量
    private TextView detailConsume;//电网用电量
    private TextView detailLoadVoltage;//负载用电量

    private TextView detailEpsVoltage;//EPS输出电压
    private TextView detailEpsCurrent;//EPS输出电流
    private TextView detailEpsFrequency;//EPS输出频率
    private TextView detailEpsPower;//EPS输出功率


    private TextView detailConsumeVoltage;//电网电压
    private TextView detailConsumeCurrent;//电网电流
    private TextView detailConsumeFrequnc;//电网频率
    private TextView detailConsumePower;//电网功率
    private TextView detailLoadPower;//负载用电功率

    private TextView tvDesc1;//绿色
    private TextView tvDesc2;//黄色
    private TextView tvDesc3;//红色
    private TextView tvDesc4;//蓝色

    private LinearLayout llDesc1;//绿色
    private LinearLayout llDesc2;//黄色
    private LinearLayout llDesc3;//红色
    private LinearLayout llDesc4;//蓝色

    private RelativeLayout rlSelectData;//

    private TextView tvCenterTitle;
    private TextView tvName;
    private TextView tvDate;
    private PcsInfoDto data;
    private TextView tvType;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvSelectDate;
    private ImageView ivHead;
    private RadioButton rbView1;
    private int dateType;
    private NestedScrollView nestedScrollView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }

    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_station_detail;
    }

    @Override
    public void initView() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        rbView1 = findViewById(R.id.rb_view1);
        dateTitle = findViewById(R.id.rg_date);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvDate = (TextView) findViewById(R.id.tv_date);

        tvDesc1 = (TextView) findViewById(R.id.tv_desc1);
        tvDesc2 = (TextView) findViewById(R.id.tv_desc2);
        tvDesc3 = (TextView) findViewById(R.id.tv_desc3);
        tvDesc4 = (TextView) findViewById(R.id.tv_desc4);

        llDesc1 = findViewById(R.id.ll_desc1);
        llDesc2 = findViewById(R.id.ll_desc2);
        llDesc3 = findViewById(R.id.ll_desc3);
        llDesc4 = findViewById(R.id.ll_desc4);

        rlSelectData = findViewById(R.id.rl_select_data);

        tvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        tvSelectDate = (TextView) findViewById(R.id.tv_select_date);
        detailVoltage1 = (TextView) findViewById(R.id.detail_voltage1);
        detailCurrent1 = (TextView) findViewById(R.id.detail_current1);
        detailPower1 = (TextView) findViewById(R.id.detail_power1);
        detailYield1 = (TextView) findViewById(R.id.detail_yield1);
        detailVoltage2 = (TextView) findViewById(R.id.detail_voltage2);
        detailCurrent2 = (TextView) findViewById(R.id.detail_current2);
        detailPower2 = (TextView) findViewById(R.id.detail_power2);
        detailYield2 = (TextView) findViewById(R.id.detail_yield2);
        detailFeed = (TextView) findViewById(R.id.detail_feed);
        detailConsume = (TextView) findViewById(R.id.detail_consume);
        detailLoadVoltage = (TextView) findViewById(R.id.detail_load_voltage);
        detailEpsVoltage = (TextView) findViewById(R.id.detail_eps_voltage);
        detailEpsCurrent = (TextView) findViewById(R.id.detail_eps_current);
        detailEpsFrequency = (TextView) findViewById(R.id.detail_eps_frequency);
        detailEpsPower = (TextView) findViewById(R.id.detail_eps_power);
        detailConsumeVoltage = (TextView) findViewById(R.id.detail_consume_voltage);
        detailConsumeCurrent = (TextView) findViewById(R.id.detail_consume_current);
        detailConsumeFrequnc = (TextView) findViewById(R.id.detail_consume_frequnc);
        detailConsumePower = (TextView) findViewById(R.id.detail_consume_power);
        detailLoadPower = (TextView) findViewById(R.id.detail_load_power);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        lineChart = findViewById(R.id.lineChart);
        barChart = findViewById(R.id.barChart);

        setNormalTitle(MyApplication.getInstance().getPowerDto().getProductName(), v -> finish());
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog(null, null, null, nestedScrollView);
            }
        });

        mLineMarket = new CostomMarket(this, lineChart, DisplayUtil.getScreenWidth(this), getResources().getDimension(R.dimen.widget_size_250), 0);
        mCharManager = new CharManager(lineChart, mLineMarket, this);
        mCharManager.setLegend(); // 设置图例

        mBarMarket = new CostomBarMarket(this, barChart);
        mCharBarManage = new CharBarManage1(barChart, mBarMarket);

        data = (PcsInfoDto) getIntent().getSerializableExtra("data");
        tvName.setText("inverter" + data.getId());

        Glide.with(this).
                load(data.getPcsPhoto())
                .into(ivHead);//在RequestBuilder 中使用自定义的ImageViewTarge

        currDate = new Date();
        tvDate.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(data.getTime())));
        initCustomTimePicker();
        tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, currDate));
    }


    @Override
    public void initData() {
        typeList.add("PV input voltage");
        typeList.add("PV input current");
        typeList.add("PV input power");

        typeList.add("Glid output voltage");
        typeList.add("Glid output frequency");
        typeList.add("Glid output current");
        typeList.add("Glid output power");

        typeList.add("PV Yield");
        typeList.add("Yield of in grid");
        typeList.add("Yield of feeding in grid");
        typeList.add("Consumption of grid ");

//        setData();
        //获取逆变器详情
        getPcsInfo();//
        //获取逆变器历史数据
        getPcsHistoryData();

    }


    public void initTab() {
    }

    private void getPcsInfo() {
        showLoadingDialog();
        userViewModel.getPcsInfo(getIntent().getStringExtra("sn"), getIntent().getStringExtra("id"));
        userViewModel.getPcsInfoDetailData().observe(this, new Observer<BaseDto<PcsInfoDetailDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PcsInfoDetailDto> pcsInfoDetailDtoBaseDto) {
                hideLoadingDialog();
                if (pcsInfoDetailDtoBaseDto.isSuccess()) {
                    PcsInfoDetailDto detailDto = pcsInfoDetailDtoBaseDto.getData();
                    if (EmptyUtils.isNotEmpty(detailDto)) {
                        initDetailView(detailDto);
                    } else {
                        initNodataView();
                    }
                } else {
                    initNodataView();
                    ToastUitl.showImageToastTips(pcsInfoDetailDtoBaseDto.getMsg());
                }
            }
        });
    }

    /**
     * private TextView detailVoltage1;//PV1输入电压
     * private TextView detailCurrent1;//PV1输入电流
     * private TextView detailPower1;//PV1输入功率
     * private TextView detailYield1;//PV1发电量
     * <p>
     * private TextView detailVoltage2;//PV2输入电压
     * private TextView detailCurrent2;//PV2输入电流
     * private TextView detailPower2;//PV2输入功率
     * private TextView detailYield2;//PV2发电量
     * <p>
     * private TextView detailFeed;//电网馈电量
     * <p>
     * private TextView detailConsume;//电网用电量
     * private TextView detailLoadVoltage;//负载用电量
     * <p>
     * <p>
     * private TextView detailEpsVoltage;//EPS输出电压
     * private TextView detailEpsCurrent;//EPS输出电流
     * private TextView detailEpsFrequency;//EPS输出频率
     * private TextView detailEpsPower;//EPS输出功率
     * <p>
     * <p>
     * private TextView detailConsumeVoltage;//电网电压
     * private TextView detailConsumeCurrent;//电网电流
     * private TextView detailConsumeFrequnc;//电网频率
     * private TextView detailConsumePower;//电网功率
     * private TextView detailLoadPower;//负载用电功率
     * 初始化详情页
     *
     * @param detailDto
     */
    private void initDetailView(PcsInfoDetailDto detailDto) {
        Log.e(TAG, "initDetailView: " + detailDto.toString());
        detailVoltage1.setText(detailDto.inputVoltPv1);//PV1输入电压
        detailCurrent1.setText(detailDto.inputCurrPv1);//PV1输入电流
        detailPower1.setText(detailDto.inputPowerPv1);//PV1输入功率
        detailYield1.setText(detailDto.inputEneryPv1);//PV1发电量

        detailVoltage2.setText(detailDto.inputVoltPv2);//PV2输入电压
        detailCurrent2.setText(detailDto.inputCurrPv2);//PV2输入电流
        detailPower2.setText(detailDto.inputPowerPv2);//PV2输入功率
        detailYield2.setText(detailDto.inputEneryPv2);//PV2发电量

        detailFeed.setText(detailDto.toGrid);//电网馈电量
        detailConsume.setText(detailDto.todayConsumption);//电网用电量
        detailLoadVoltage.setText(detailDto.loadConsumption);//负载用电量

        detailEpsVoltage.setText(detailDto.loadOutVolt);//EPS输出电压
        detailEpsCurrent.setText(detailDto.loadOutCurr);//EPS输出电流
        detailEpsFrequency.setText(detailDto.loadOutFreq);//EPS输出频率
        detailEpsPower.setText(detailDto.loadOutPower);//EPS输出功率


        detailConsumeVoltage.setText(detailDto.gridVolt);//电网电压
        detailConsumeCurrent.setText(detailDto.gridCurr);//电网电流
        detailConsumeFrequnc.setText(detailDto.gridFreq);//电网频率
        detailConsumePower.setText(detailDto.gridPower);//电网功率
        detailLoadPower.setText(detailDto.loadActivePower);//负载用电功率


    }

    @Override
    public void initListener() {
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();
            }
        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getBeforeDay(currDate));

            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPcsHistoryData();
                loadDatas(DateUtil.getAfterDay(currDate));
            }
        });
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });

        dateTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_view1:
                        dateType = 0;
                        break;
                    case R.id.rb_view2:
                        dateType = 1;
                        break;
                    case R.id.rb_view3:
                        dateType = 2;
                        break;
                    case R.id.rb_view4:
                        dateType = 3;
                        rlSelectData.setVisibility(View.GONE);
                        break;
                }
                loadDatas(currDate);
            }
        });

        mLineMarket.setOnMarketBackCall(new BaseMarket.OnMarketBackCall() {
            @Override
            public String onBackCall(int position) {
                if (Utils.isInBound(mDataDto, position)) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    return format.format(new Date(mDataDto.get(position).getTime()));
                }
                return "";
            }
        });
        mBarMarket.setOnMarketBackCall(new BaseMarket.OnMarketBackCall() {
            @Override
            public String onBackCall(int position) {
                if (Utils.isInBound(mDataDto, position)) {
                    if (dateType == 1) {
                        SimpleDateFormat format = new SimpleDateFormat("dd");
                        return format.format(new Date(mDataDto.get(position).getTime())) + "day";
                    } else if (dateType == 2) {
                        SimpleDateFormat format = new SimpleDateFormat("MM");
                        String dateStr = format.format(new Date(mDataDto.get(position).getTime()));
                        return CharDateManager.getMonth(Integer.parseInt(dateStr));
                    } else if (dateType == 3) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy");
                        return format.format(new Date(mDataDto.get(position).getTime()));
                    }

                }
                return "";
            }
        });

    }

    /**
     * 请求
     */
    private Date currDate;

    private void loadDatas(Date date) {
        currDate = date;
        tvRight.setVisibility(DateUtil.isBeforCurrentDate(currDate, 3) ? View.VISIBLE : View.INVISIBLE);
        tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, currDate));
        getPcsHistoryData();
    }

    public void showDesc(String desc1, String desc2, String desc3, String desc4) {
        if (desc1 == null) {
            llDesc1.setVisibility(View.GONE);
        } else {
            llDesc1.setVisibility(View.VISIBLE);
            tvDesc1.setText(desc1);
        }
        if (desc2 == null) {
            llDesc2.setVisibility(View.GONE);
        } else {
            llDesc1.setVisibility(View.VISIBLE);
            tvDesc2.setText(desc2);
        }
        if (desc3 == null) {
            llDesc3.setVisibility(View.GONE);
        } else {
            llDesc3.setVisibility(View.VISIBLE);
            tvDesc3.setText(desc3);
        }
        if (desc4 == null) {
            llDesc4.setVisibility(View.GONE);
        } else {
            llDesc4.setVisibility(View.VISIBLE);
            tvDesc4.setText(desc4);
        }
    }

    //显示2条柱状图
    private void showBarChartMore(List<PcsHistoryDataDto> rows) {
        mCharBarManage.removeAll();
        if (Utils.isEmpty(rows)){
            return;
        }
        List<Float> xAxisValues = new ArrayList<>();
        List<List<Float>> yAxisValues = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<Integer> colours = new ArrayList<>();

        List<Float> yVals1 = new ArrayList<>();
        List<Float> yVals2 = new ArrayList<>();
        List<Float> yVals3 = new ArrayList<>();
        float maxValue = 0;
        float minValue = 0;
        for (int index = 0, len = rows.size(); index < len; index++) {
            mDataDto = rows;
            PcsHistoryDataDto dto = rows.get(index);
            xAxisValues.add((float) index);
            float value1 = 0;
            float value2 = 0;
            float value3 = 0;
            switch (type) {
                case 7:
                    value1 = mCharBarManage.getFloatValue(dto.getInputCurrPv1());
                    value2 = mCharBarManage.getFloatValue(dto.getInputCurrPv2());
                    value3 = mCharBarManage.getFloatValue(dto.getInputEneryPv());
                    yVals1.add(value1);
                    yVals2.add(value2);
                    yVals3.add(value3);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    value1 = mCharBarManage.getFloatValue(dto.getInputCurrPv1());
                    yVals1.add(value1);
                    yVals2.add(value2);
                    yVals3.add(value3);
            }

        }
        String mUntil = "";
        switch (type) {
            case 7:
                yAxisValues.add(yVals1);
                yAxisValues.add(yVals2);
                yAxisValues.add(yVals3);
                labels.add("PV1");
                labels.add("PV2");
                labels.add("Total");
                colours.add(getResources().getColor(R.color.color_chart_three));
                colours.add(getResources().getColor(R.color.color_chart_four));
                colours.add(getResources().getColor(R.color.color_chart_one));
                break;
            case 8:
                yAxisValues.add(yVals1);
                labels.add("Capacity");
                colours.add(getResources().getColor(R.color.color_chart_one));
                break;
            case 9:
                yAxisValues.add(yVals1);
                labels.add("Power");
                colours.add(getResources().getColor(R.color.color_chart_one));
                break;
            case 10:
                yAxisValues.add(yVals1);
                labels.add("Power");
                colours.add(getResources().getColor(R.color.color_chart_one));
                break;
            case 11:
                yAxisValues.add(yVals1);
                labels.add("Voltage");
                colours.add(getResources().getColor(R.color.color_chart_three));
                break;
            case 12:
                yAxisValues.add(yVals1);
                labels.add("Frequency");
                colours.add(getResources().getColor(R.color.color_chart_two));
                break;
            case 13:
                yAxisValues.add(yVals1);
                labels.add("Current");
                colours.add(getResources().getColor(R.color.color_chart_one));
                break;
            case 14:
                yAxisValues.add(yVals1);
                labels.add("Power");
                colours.add(getResources().getColor(R.color.color_chart_four));
                break;
        }
        xAxisValues.add((float) xAxisValues.size());
        mCharBarManage.showMoreBarChart(mUntil, xAxisValues, yAxisValues, labels, colours, this)
                .setXAxis(xAxisValues.size() - 1, 0, xAxisValues.size())
                .invalidate()
                .setScalX(mDataDto.size());

    }

    /**
     * @param rows
     */
    public void setLineData(List<PcsHistoryDataDto> rows) {
        LineData lineData = new LineData();
        mCharManager.removeAll();
        if (rows != null && !rows.isEmpty()) {
            mDataDto = rows;
            mCharManager.setXAxis(rows.size(), 0, 1, rows.size(), this); // 设置X轴
            List<Entry> yVals1 = new ArrayList<>();
            List<Entry> yVals2 = new ArrayList<>();
            List<Entry> yVals3 = new ArrayList<>();
            mLineMarket.clear();
            for (int index = 0, len = rows.size(); index < len; index++) {
                PcsHistoryDataDto dto = rows.get(index);
                float value1 = 0;
                float value2 = 0;
                float value3 = 0;

                switch (type) {
                    case 0:
                        value1 = mCharBarManage.getFloatValue(dto.getInputVoltPv1());
                        value2 = mCharBarManage.getFloatValue(dto.getInputVoltPv2());
                        yVals1.add(new Entry(index, value1));
                        yVals2.add(new Entry(index, value2));
                        break;
                    case 1:
                        value1 = mCharBarManage.getFloatValue(dto.getInputPowerPv1());
                        value2 = mCharBarManage.getFloatValue(dto.getInputPowerPv2());
                        yVals1.add(new Entry(index, value1));
                        yVals2.add(new Entry(index, value2));
                        break;
                    case 2:
                        value1 = mCharBarManage.getFloatValue(dto.getInputCurrPv1());
                        value2 = mCharBarManage.getFloatValue(dto.getInputCurrPv2());
                        yVals1.add(new Entry(index, value1));
                        yVals2.add(new Entry(index, value2));
                        break;
                    case 3:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 4:
                        value1 = mCharBarManage.getFloatValue(dto.getGridFreq());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 5:
                        value1 = mCharBarManage.getFloatValue(dto.getGridCurr());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 6:
                        value1 = mCharBarManage.getFloatValue(dto.getGridPower());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 7:
                        value1 = mCharBarManage.getFloatValue(dto.getInputCurrPv1());
                        value2 = mCharBarManage.getFloatValue(dto.getInputCurrPv2());
                        value3 = mCharBarManage.getFloatValue(dto.getInputEneryPv());
                        yVals1.add(new Entry(index, value1));
                        yVals2.add(new Entry(index, value2));
                        yVals3.add(new Entry(index, value3));
                        break;
                    case 8:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 9:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 10:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 11:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 12:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 13:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                    case 14:
                        value1 = mCharBarManage.getFloatValue(dto.getGridVolt());
                        yVals1.add(new Entry(index, value1));
                        break;
                }

            }
            String mUntil = "";
            switch (type) {
                case 0:
                case 1:
                case 2:
                    lineData.addDataSet(mCharManager.setChartData("PV1", yVals1, R.color.color_chart_four, R.drawable.bg_color4));
                    lineData.addDataSet(mCharManager.setChartData("PV2", yVals2, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 3:
                    lineData.addDataSet(mCharManager.setChartData("Voltage", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
                    break;
                case 4:
                    lineData.addDataSet(mCharManager.setChartData("Freguency", yVals1, R.color.color_chart_two, R.drawable.bg_color2));
                    break;
                case 5:
                    lineData.addDataSet(mCharManager.setChartData("Power", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 6:
                    lineData.addDataSet(mCharManager.setChartData("PV1", yVals1, R.color.color_chart_four, R.drawable.bg_color4));
                    break;
                case 7:
                    lineData.addDataSet(mCharManager.setChartData("Pv1", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
                    lineData.addDataSet(mCharManager.setChartData("Pv2", yVals2, R.color.color_chart_four, R.drawable.bg_color4));
                    lineData.addDataSet(mCharManager.setChartData("Total", yVals3, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 8:
                    lineData.addDataSet(mCharManager.setChartData("Power", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 9:
                    lineData.addDataSet(mCharManager.setChartData("Power", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 10:
                    lineData.addDataSet(mCharManager.setChartData("Power", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 11:
                    lineData.addDataSet(mCharManager.setChartData("Voltage", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 12:
                    lineData.addDataSet(mCharManager.setChartData("Frequency", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 13:
                    lineData.addDataSet(mCharManager.setChartData("Current", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 14:
                    lineData.addDataSet(mCharManager.setChartData("Power", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
            }
            Log.e(TAG, "initDataView: " + yVals1.size() + "-" + yVals2.size() + "-" + yVals3.size());
            mCharManager.setYAxis(mDataDto.size() - 1, 0, mDataDto.size()); // 设置Y轴
            mCharManager.setData(lineData, mUntil, mDataDto.size());

        }
    }


    private List<String> typeList = new ArrayList<>();
    private int type = 0;// 统计类型（0:pv输入电压，1:PV输入电流，2:pv输入功率，3:电网输出电压，4:电网输出频率，
    // 5:电网输出电流，6:电网输出功率，7:pv发电量，8:馈电网电量，9:电网用电量，10:负载用电量）

    private void showTypeDialog() {

        NiceDialog.init()
                .setLayoutId(R.layout.dialog_select_inver)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);
                        WheelView wheelview = (WheelView) holder.getView(R.id.wheelview);
                        wheelview.setCyclic(false);
                        TextView tvTitle = (TextView) holder.getView(R.id.tv_title);
                        TextView tvCancel = (TextView) holder.getView(R.id.tv_cancel);

                        TextView tvFinish = (TextView) holder.getView(R.id.tv_finish);
                        wheelview.setAdapter(new WheelAdapter(typeList));

                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tvType.setText(typeList.get(wheelview.getCurrentItem()));
                                type = wheelview.getCurrentItem();
                                rbView1.setChecked(true);
                                setCenterTitle();
                                dateType = 0;
                                rlSelectData.setVisibility(View.VISIBLE);
                                dialog.dismiss();
                                loadDatas(currDate);
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        wheelview.setCurrentItem(0);
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void setCenterTitle() {
        switch (type) {
            case 0:
                tvCenterTitle.setText("Voltage（A）");
                showDesc(null, null, "PV1", "PV2");
                break;
            case 1:
                tvCenterTitle.setText("Current（A）");
                showDesc(null, null, "PV1", "PV2");
                break;
            case 2:
                tvCenterTitle.setText("Power（W）");
                showDesc(null, null, "PV1", "PV2");
                break;
            case 3:
                tvCenterTitle.setText("Voltage（A）");
                showDesc(null, null, null, null);
                break;
            case 4:
                tvCenterTitle.setText("Frequency（Hz）");
                showDesc(null, null, null, null);
                break;
            case 5:
                tvCenterTitle.setText("Current（A）");
                showDesc(null, null, null, null);
                break;
            case 6:
                tvCenterTitle.setText("Power（W）");
                showDesc("PV1", null, "PV2", "Total");
                break;
            case 7:
                tvCenterTitle.setText("Power（W）");
                showDesc(null, null, null, null);
                break;
            case 8:
                tvCenterTitle.setText("Capacity（kWh）");
                showDesc(null, null, null, "Feed network capacit");
                break;
            case 9:
                tvCenterTitle.setText("Capacity（kWh）");
                showDesc(null, null, null, "Feed network capacit");
                break;
            case 10:
                tvCenterTitle.setText("Power（W）");
                showDesc(null, null, null, "Electricity consumption in power grid");
                break;

        }
    }

    private TimePickerView pvCustomTime;

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
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, date));
                loadDatas(date);
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

    /**
     * 获取逆变器图形数据
     */
    private void getPcsHistoryData() {
        showLoadingDialog();
        userViewModel.getPcsHistoryData(getIntent().getStringExtra("sn"), getIntent().getStringExtra("id"), type, tvSelectDate.getText().toString());
        userViewModel.getPcsHistoryData().observe(this, new Observer<BaseDto<PcsHistoryDataListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PcsHistoryDataListDto> dtoBaseDto) {
                hideLoadingDialog();
                if (dtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(dtoBaseDto.getData()) && EmptyUtils.isNotEmpty(dtoBaseDto.getData().getRows())) {
                        initDataView(dtoBaseDto.getData().getRows());
                    }
                } else {
                    ToastUitl.showImageToastTips(dtoBaseDto.getMsg());
                }
            }
        });

    }

    /**
     * 没历史数据的时候
     */
    private void initNodataView() {
        if (type == 0) {
            lineChart.setVisibility(View.VISIBLE);
            barChart.setVisibility(View.GONE);
            mCharManager.removeAll();
        } else {
            lineChart.setVisibility(View.GONE);
            barChart.setVisibility(View.VISIBLE);
            mCharBarManage.clear();
        }
    }

    /**
     * 有历史数据的时候 初始化图形
     *
     * @param rows
     */
    private void initDataView(List<PcsHistoryDataDto> rows) {
        Log.e(TAG, "initDataView: " + rows.size());
        switch (type) {
            case 7:
            case 8:
            case 9:
            case 10:
                if (dateType == 0) {
                    dateTitle.setVisibility(View.VISIBLE);
                    lineChart.setVisibility(View.VISIBLE);
                    barChart.setVisibility(View.GONE);
                    setLineData(rows);
                } else {
                    dateTitle.setVisibility(View.VISIBLE);
                    lineChart.setVisibility(View.GONE);
                    barChart.setVisibility(View.VISIBLE);
                    showBarChartMore(rows);
                }
                break;
            default:
                dateTitle.setVisibility(View.GONE);
                lineChart.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
                setLineData(rows);
                break;
        }
    }


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        int currItem = (int) value;
        switch (dateType) {
            case 0:
                format = new SimpleDateFormat("HH:mm");
                break;
            case 1:
                format = new SimpleDateFormat("dd");
                break;
            case 2:
                format = new SimpleDateFormat("MM");
                break;
            case 3:
                format = new SimpleDateFormat("yyyy");
                break;
        }
        if (currItem >= 0 && currItem < mDataDto.size()) {
            return format.format(new Date(mDataDto.get(currItem).getTime()));
        } else {
            return "";
        }
    }


    private List<PcsHistoryDataDto> mDataDto = new ArrayList<>();
}
