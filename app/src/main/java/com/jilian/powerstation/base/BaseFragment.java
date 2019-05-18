package com.jilian.powerstation.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.dialog.LoadingDialog;
import com.jilian.powerstation.utils.SPUtil;



/**
 * Fragment 基类
 *
 * @author weishixiong
 * @Time 2018-03-19
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected final String TAG = this.getClass().getSimpleName();

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
    /**
     * 视图是否已经初初始化
     */
    private boolean isInit = false;
    private boolean isLoad = false;
    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            loadData();
            isLoad = true;
        } else {
            if (isLoad) {
                //stopLoad();
            }
        }
    }
    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }
    @Override
    public void onResume() {
        super.onResume();
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }
    public LoginDto getLoginDto(){
        return SPUtil.getData(Constant.SP_VALUE.SP,Constant.SP_VALUE.LOGIN_DTO,LoginDto.class,null);
    }


    protected abstract void loadData();


    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);

        createViewModel();
        initView(view, savedInstanceState);
        initData();
        initListener();

        return view;
    }



    /**
     * 创建presenter
     */
    protected abstract void createViewModel();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();


    public void setNormalTitle(View view,String title, View.OnClickListener backListener) {
        setCenterTitle(view,title, "#FFFFFF");
        setleftImage(view,R.drawable.image_back, true, backListener);
        setTitleBg(view,R.color.color_main_select);
    }

    /**
     * 设置中间标题
     **/
    public void setCenterTitle(View view,String text, String color) {
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);


    }
    /**
     * 设置左边图片
     **/
    public void setleftImage(View view,int resource, boolean isfinish, View.OnClickListener listener) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_left_text);
        imageView.setImageResource(resource);
        imageView.setVisibility(View.VISIBLE);
        if (isfinish) {
            view.findViewById(R.id.v_back).setVisibility(View.VISIBLE);
            imageView.setOnClickListener(listener);
        } else {
            view.findViewById(R.id.v_back).setVisibility(View.GONE);

        }

    }
    /**
     * 设置背景图片
     **/
    public void setTitleBg(View view,int color) {
        LinearLayout lLtitle = (LinearLayout) view.findViewById(R.id.ll_title);
        lLtitle.setBackgroundResource(color);

    }

    /**
     * 设置左边标题
     **/
    public void setleftTitle(View view,String text, String color, boolean isfinish, View.OnClickListener listener) {
        TextView textView = (TextView) view.findViewById(R.id.tv_left_text);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        if (isfinish) {
            view.findViewById(R.id.v_back).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.v_back).setVisibility(View.GONE);
            textView.setOnClickListener(listener);
        }

    }

    /**
     * 设置右边标题
     **/
    public void setrightTitle(View view,String text, String color, View.OnClickListener listener) {
        TextView textView = (TextView) view.findViewById(R.id.tv_right_text);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setOnClickListener(listener);
        textView.setVisibility(View.VISIBLE);

    }

    private LoadingDialog loadingDialog;//加载提示框


    public LoadingDialog getLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(getActivity());
            //点击空白处Dialog不消失
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }

    public void showLoadingDialog() {

        getLoadingDialog().showDialog();
    }

    public void hideLoadingDialog()  {
        getLoadingDialog().dismiss();
    }

}
