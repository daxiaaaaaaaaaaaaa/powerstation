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
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.OnRecycleItemListener;

import java.util.List;

public class ConnectedsAdapter extends RecyclerView.Adapter<ConnectedsAdapter.ESSListHolder> {

    private List<ESSDto> mDatas;
    private Context context;
    private OnRecycleItemListener itemListener;


    public ConnectedsAdapter(List<ESSDto> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_connected_device, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ESSListHolder essListHolder, int i) {
        essListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (itemListener != null) {
                    itemListener.OnItemClick(v, i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setItemListener(OnRecycleItemListener itemListener) {
        this.itemListener = itemListener;
    }

    class ESSListHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView powerToday;//当天发电
        private TextView powerTotal;//总共发电
        private TextView rated;//光伏功率
        private TextView address;
        private ImageView cover;

        public ESSListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.connected_device_title);
            powerTotal = itemView.findViewById(R.id.connected_device_day);
            rated = itemView.findViewById(R.id.connected_device_rated);
            address = itemView.findViewById(R.id.connected_device_lifetime);
            cover = itemView.findViewById(R.id.connected_device_cover);
        }
    }
}
