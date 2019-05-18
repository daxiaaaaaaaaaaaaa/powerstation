package com.jilian.powerstation.utils.selectphoto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者：weishixiong
 * @date 创建时间：2018/3/28
 * @corporation 公司：mujinkeji
 * @desception 图像选择工具类
 */

public class SelectPhotoUtils {
    
    private static ExMediaStoreCompat mMediaStoreCompat;
    
    /**
     * 从图库选择
     *
     * @param activity
     */
    public static void fromAlbum(Activity activity, String authorities, int count, int requestCode) {
        Matisse.from(activity)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .countable(false)
                //是否要照相功能
                .capture(false)
                .captureStrategy(
                        new CaptureStrategy(true, authorities))
                .maxSelectable(count)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(requestCode);
    }
    
    /**
     * 从图库选择
     *
     * @param fragment
     */
    public static void fromAlbum(Fragment fragment, String authorities, int count, int requestCode) {
        Matisse.from(fragment)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .countable(false)
                //是否要照相功能
                .capture(false)
                .captureStrategy(
                        new CaptureStrategy(true, authorities))
                .maxSelectable(count)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        fragment.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(requestCode);
    }
    
    /**
     * 从相机选择
     *
     * @param activity
     */
    public static void fromCapture(BaseActivity activity, String authorities, int requestCode) {
        mMediaStoreCompat = new ExMediaStoreCompat(activity);
        mMediaStoreCompat.setCaptureStrategy(new CaptureStrategy(true, authorities));
        mMediaStoreCompat.dispatchCaptureIntent(activity, requestCode);
    }
    
    /**
     * 从相机选择
     *
     * @param fragment
     */
    public static void fromCapture(BaseActivity activity, BaseFragment fragment, String authorities, int requestCode) {
        mMediaStoreCompat = new ExMediaStoreCompat(activity, fragment);
        mMediaStoreCompat.setCaptureStrategy(new CaptureStrategy(true, authorities));
        mMediaStoreCompat.dispatchCaptureIntent(fragment.getActivity(), requestCode);
    }
    
    /**
     * 得到图库选择的结果
     *
     * @param data
     * @return
     */
    public static List<Uri> albumResult(Intent data) {
        List<Uri> uriList = new ArrayList<>();
        if (data != null) {
            uriList = Matisse.obtainResult(data);
        }
        return uriList;
    }
    
    /**
     * 得到相册择的Uri
     * 在onActivityResult后调用，否者无数据
     *
     * @return
     */
    public static Uri captureUriResult() {
        Uri contentUri = null;
        if (mMediaStoreCompat != null) {
            contentUri = mMediaStoreCompat.getCurrentPhotoUri();
        }
        return contentUri;
    }
    
    /**
     * 得到相册选择的Path
     * 在onActivityResult后调用，否者无数据
     *
     * @return
     */
    public static String capturePathResult() {
        String path = "";
        if (mMediaStoreCompat != null) {
            path = mMediaStoreCompat.getCurrentPhotoPath();
        }
        return path;
    }
    
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
