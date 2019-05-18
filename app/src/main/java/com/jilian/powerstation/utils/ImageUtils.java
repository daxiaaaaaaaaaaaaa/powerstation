package com.jilian.powerstation.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtils {

    /**
     * 创建一个 Uri，用于存储拍照后的照片
     */
    private static Uri createImageUri(Context context) {
        String name = "takePhoto" + System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, name);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return uri;
    }

    /**
     * 删除本地 Uri
     */
    public static void delteImageUri(Context context, Uri uri) {
        context.getContentResolver().delete(uri, null, null);
    }

    /**
     * 获取压缩图片的options
     *
     * @return
     */
    public static BitmapFactory.Options getOptions(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4; //此项参数可以根据需求进行计算
        options.inJustDecodeBounds = false;
        return options;
    }

    /**
     * 根据拍照得到的 Uri 获取压缩后的 Bitmap.
     *
     * @param imageUri
     * @return
     */
    public static Bitmap getBitmapByUri(Uri imageUri) {
        return BitmapFactory.decodeFile(imageUri.getPath(), getOptions(imageUri.getPath()));
    }

    public static Bitmap getBitmapByUrl(String url) {
        return BitmapFactory.decodeFile(url, getOptions(url));
    }

    /**
     * 打开相机，拍照后直接返回 Bitmap 对象.
     * @param mActivity
     * @param requestCode
     * @return
     */
    public static void takePhotoReturnBitmap(Activity mActivity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍照
     */
    public static Uri takePhoto(Activity mActivity, int requestCode) throws IOException {
        //指定拍照intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = null;
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            String sdcardState = Environment.getExternalStorageState();
            File outputImage = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                outputImage = createImageFile(mActivity);
            } else {
                Toast.makeText(mActivity.getApplicationContext(), "内存异常", Toast.LENGTH_SHORT).show();
            }
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (outputImage != null) {
                imageUri = Uri.fromFile(outputImage);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                mActivity.startActivityForResult(takePictureIntent, requestCode);
            }
        }
        return imageUri;
    }

    public static File createImageFile(Activity mActivity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;//创建以时间命名的文件名称
        File storageDir = getOwnCacheDirectory(mActivity, "takephoto");
        //创建保存的路径
        File image = new File(storageDir.getPath(), imageFileName + ".jpg");
        if (!image.exists()) {
            try { //在指定的文件夹中创建文件
                image.createNewFile();
            } catch (Exception e) {
            }
        }
        return image;
    }

    /**
     * 根据目录创建文件夹 * @param context * @param cacheDir * @return
     */
    public static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null; //判断sd卡正常挂载并且拥有权限的时候创建文件
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        }
        if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    /**
     * 检查是否有权限 * @param context * @return
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }


}
