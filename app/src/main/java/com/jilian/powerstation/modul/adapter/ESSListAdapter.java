package com.jilian.powerstation.modul.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.listener.CustomItemClickListener;

import java.util.List;

public class ESSListAdapter extends RecyclerView.Adapter<ESSListAdapter.ViewHolder> {

    private List<PowerDto> mDatas;
    private Context mContext;
    private CustomItemClickListener listener;

    public ESSListAdapter(List<PowerDto> mDatas, Context context, CustomItemClickListener listener) {
        this.mDatas = mDatas;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_power_station, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mDatas.get(position).getProductName());//电站名称
        holder.powerToday.setText(mDatas.get(position).getTodayPVproduction());//当天发电
        holder.powerTotal.setText(mDatas.get(position).getHistoryPVproduction());//总共发电
        holder.rated.setText(mDatas.get(position).getRated_power());//光伏额定功率
        holder.address.setText(mDatas.get(position).getAddress());//地址

        Glide.with(mContext).
                load(mDatas.get(position).getPhotopath())
                .into(holder.cover);//在RequestBuilder 中使用自定义的ImageViewTarge

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;//名字
        private TextView powerToday;//当天发电
        private TextView powerTotal;//总共发电
        private TextView rated;//光伏功率
        private TextView address;
        private ImageView cover;

        public ViewHolder(@NonNull View itemView, final CustomItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.power_station_title);
            powerToday = (TextView) itemView.findViewById(R.id.power_station_day);
            powerTotal = itemView.findViewById(R.id.power_station_lifetime);
            rated = itemView.findViewById(R.id.power_station_rated);
            address = itemView.findViewById(R.id.power_station_address);
            cover = itemView.findViewById(R.id.power_station_cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(itemView, getAdapterPosition());
                }
            });
        }
    }
}
