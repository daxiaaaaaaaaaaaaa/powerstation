package com.jilian.powerstation.manege;

import com.github.mikephil.charting.components.IMarker;

/**
 * Created by cxz on 2019-07-29
 * <p>
 * Discrebe:
 */
public abstract class BaseMarket implements IMarker{
    public   OnMarketBackCall onMarketBackCall;
    public boolean isEnable = true;

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setOnMarketBackCall(OnMarketBackCall onMarketBackCall) {
        this.onMarketBackCall = onMarketBackCall;
    }

    public interface OnMarketBackCall{
        String onBackCall(int position);
    }
}
