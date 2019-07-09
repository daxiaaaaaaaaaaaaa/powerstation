package com.jilian.powerstation.modul.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.DeviceAlarmInfoDto;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.OnRecycleItemListener;
import com.jilian.powerstation.utils.DateUtil;

import java.util.Date;
import java.util.List;

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.ESSListHolder> {

    private List<DeviceAlarmInfoDto> mDatas;
    private Context context;
    private OnRecycleItemListener itemListener;
    private int type;

    public WarningAdapter(List<DeviceAlarmInfoDto> mDatas, Context context, int type) {
        this.mDatas = mDatas;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_warning, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ESSListHolder holder, int i) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (itemListener != null) {
                    itemListener.OnItemClick(v, i);
                }
            }
        });
        if(type==0){
            holder.tvName.setText("inverter" + mDatas.get(i).getId());
        }
        if(type==1){
            holder.tvName.setText("Battery" + mDatas.get(i).getId());
        }
        if(type==2){
            holder.tvName.setText("Intelligent device" + mDatas.get(i).getId());
        }

        holder.tvCurrentArm.setText(mDatas.get(i).getNowFault());
        holder.tvHisArm.setText(mDatas.get(i).getHistoryFault());
        holder.tvDate.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss",new Date(mDatas.get(i).getTime())));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setItemListener(OnRecycleItemListener itemListener) {
        this.itemListener = itemListener;
    }

    class ESSListHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvCurrentArm;
        private TextView tvHisArm;
        private TextView tvDate;


        public ESSListHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCurrentArm = (TextView) itemView.findViewById(R.id.tv_current_arm);
            tvHisArm = (TextView) itemView.findViewById(R.id.tv_his_arm);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
