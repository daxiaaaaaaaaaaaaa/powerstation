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

import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.ESSDto;
import com.jilian.powerstation.listener.CustomItemClickListener;

import java.util.List;

/**
 * 设备调试
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ESSListHolder> {

    private List<ESSDto> mDatas;
    private Context context;
    private CustomItemClickListener listener;

    int type = 0; // 是调试列表的 0:Inverter  1:Battery 2:Smart

    public DataAdapter(List<ESSDto> mDatas, Context context, int type,CustomItemClickListener listener) {
        this.mDatas = mDatas;
        this.context = context;
        this.listener = listener;
        this.type = type;
    }

    @NonNull
    @Override
    public ESSListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ESSListHolder(LayoutInflater.from(context).inflate(R.layout.item_data, viewGroup, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ESSListHolder essListHolder, int i) {
        String  unit = "";
        switch (type) {
            case 0:
                unit = "kw";
                essListHolder.llMsg1.setVisibility(View.VISIBLE);
                essListHolder.llMsg2.setVisibility(View.VISIBLE);
                essListHolder.llMsg3.setVisibility(View.GONE);
                essListHolder.tvMsg1.setText("Rated power：");
                essListHolder.tvMsg2.setText("Power now：");
                break;
            case 1:
                unit = "kw";
                essListHolder.llMsg1.setVisibility(View.VISIBLE);
                essListHolder.llMsg2.setVisibility(View.VISIBLE);
                essListHolder.llMsg3.setVisibility(View.GONE);

                essListHolder.tvMsg1.setText("Charging power：");
                essListHolder.tvMsg2.setText("SOC：");
                break;
            case 2:
                unit = "";
                essListHolder.llMsg1.setVisibility(View.VISIBLE);
                essListHolder.llMsg2.setVisibility(View.GONE);
                essListHolder.llMsg3.setVisibility(View.GONE);
                essListHolder.tvMsg1.setText("working condition: ");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ESSListHolder extends RecyclerView.ViewHolder {

        private ImageView imgCover;
        private TextView tvName;
        private TextView tvValue1;
        private TextView tvValue2;
        private TextView tvValue3;

        private TextView tvMsg1;//
        private TextView tvMsg2;//
        private TextView tvMsg3;//

        private LinearLayout llMsg1;//
        private LinearLayout llMsg2;//
        private LinearLayout llMsg3;//

        public ESSListHolder(@NonNull View itemView,CustomItemClickListener listener) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.item_cover);

            tvName = itemView.findViewById(R.id.item_title);

            tvValue1 = itemView.findViewById(R.id.tv_item_value1);
            tvValue2 = itemView.findViewById(R.id.tv_item_value2);
            tvValue3 = itemView.findViewById(R.id.tv_item_value3);

            tvMsg1 = itemView.findViewById(R.id.tv_item_msg1);
            tvMsg2 = itemView.findViewById(R.id.tv_item_msg2);
            tvMsg3 = itemView.findViewById(R.id.tv_item_msg3);

            llMsg1 = itemView.findViewById(R.id.ll_item_msg1);
            llMsg2 = itemView.findViewById(R.id.ll_item_msg2);
            llMsg3 = itemView.findViewById(R.id.ll_item_msg3);
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
