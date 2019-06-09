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

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.ESSListHolder> {

    private List<ESSDto> mDatas;
    private Context context;
    private OnRecycleItemListener itemListener;


    public WarningAdapter(List<ESSDto> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_warning, viewGroup, false));
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


        public ESSListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
