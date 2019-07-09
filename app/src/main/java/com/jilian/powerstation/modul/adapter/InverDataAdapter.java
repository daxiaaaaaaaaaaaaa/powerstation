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
import com.jilian.powerstation.common.dto.PcsInfoDto;
import com.jilian.powerstation.listener.CustomItemClickListener;

import java.util.List;

/**
 * 逆变器列表
 */
public class InverDataAdapter extends RecyclerView.Adapter<InverDataAdapter.ESSListHolder> {

    private List<PcsInfoDto> mDatas;
    private Context context;
    private CustomItemClickListener listener;



    public InverDataAdapter(List<PcsInfoDto> mDatas, Context context, CustomItemClickListener listener) {
        this.mDatas = mDatas;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_inver_data, viewGroup, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ESSListHolder holder, int position) {
        holder.tvName.setText("inverter" + mDatas.get(position).getId());
        holder.tvOne.setText(mDatas.get(position).getRatePower());//额定功率
        holder.tvTwo.setText(mDatas.get(position).getNowChargePower());//当前放电功率


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
