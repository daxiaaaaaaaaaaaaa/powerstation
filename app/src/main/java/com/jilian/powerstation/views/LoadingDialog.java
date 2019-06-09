package com.jilian.powerstation.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


import com.jilian.powerstation.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Description 加载框
 *
 * @author weishixiong
 * @Time 2018-03-20
 */
public class LoadingDialog extends Dialog {
    private AVLoadingIndicatorView avi;
    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commom_loading_layout);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(INDICATORS[22]);
    }

    //显示对话框
    public void showDialog() {
        show();
    }

    @Override
    public void dismiss() {
        super.dismiss();


    }
    private static final String[] INDICATORS=new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
    };
}
