package com.jilian.powerstation.modul.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.BatteryfoDto;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.CustomItemClickListener;

import java.util.List;

/**
 * 设备调试
 */
public class BatteryDataAdapter extends RecyclerView.Adapter<BatteryDataAdapter.ESSListHolder> {

    private List<BatteryfoDto> mDatas;
    private Context context;
    private CustomItemClickListener listener;



    public BatteryDataAdapter(List<BatteryfoDto> mDatas, Context context, CustomItemClickListener listener) {
        this.mDatas = mDatas;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_battery_data, viewGroup, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ESSListHolder holder, int position) {


        holder.tvName.setText("battery" + mDatas.get(position).getId());
        holder.tvOne.setText(mDatas.get(position).getSoc());//充电功率
        holder.tvTwo.setText(mDatas.get(position).getPower());//当前电量


        Glide.with(context).
                load(mDatas.get(position).getBcmuPhoto()).error(R.drawable.ic_launcher_background) //异常时候显示的图片
                .placeholder(R.drawable.ic_launcher_background) //加载成功前显示的图片
                .fallback(R.drawable.ic_launcher_background) //url为空的时候,显示的图片
                .into(holder.ivHead);//在RequestBuilder 中使用自定义的ImageViewTarge

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ESSListHolder extends RecyclerView.ViewHolder {


        private ImageView ivHead;
        private TextView tvName;
        private TextView tvOne;
        private TextView tvTwo;


        public ESSListHolder(@NonNull View itemView,CustomItemClickListener listener) {
            super(itemView);



            ivHead = (ImageView)itemView. findViewById(R.id.iv_head);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvOne = (TextView) itemView.findViewById(R.id.tv_one);
            tvTwo = (TextView) itemView.findViewById(R.id.tv_two);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onItemClick(v,getAdapterPosition());
                    }
                }
            });


        }
    }
}
