package com.jinhu.toptoday.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinhu.toptoday.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * 类的用途：设定头像，拍照或获取相册照片
 * Created by jinhu
 * 2017/4/13  18:55
 */

public class ProtraitUtils {

    private static File sFile;
    public static String PHONE_PATH = Environment.getExternalStorageDirectory() + "/w65/icon_bitmap/" + "myicon.jpg";

    /**
     * initPhone 设定头像，拍照或获取相册照片
     * 设置头像的控件的 监听中 调用
     *
     * @param context 上下文
     */
    public static void initPhone(final Context context) {
        //获取相册，设置头像
        Toast.makeText(context, "设置头像", Toast.LENGTH_SHORT).show();
        //选项，相册或拍照
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 1:
                        //打开本地相册
                        getLocalImage((Activity) context, 102);
                        break;
                    case 0:
                        //打开相机
                        readXiangji((Activity) context, 103);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * readXiangji 打开相机
     *
     * @param context 上下文
     * @param code    requestCode
     */
    private static void readXiangji(Activity context, int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //文件的保存位置
        sFile = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        if (!sFile.exists()) {
            try {
                sFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置图片拍摄后保存的位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(sFile));
        //回传值
        context.startActivityForResult(intent, code);
    }

    /**
     * getLocalImage 打开本地相册
     *
     * @param context 上下文
     * @param code    requestCode
     */
    private static void getLocalImage(Activity context, int code) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(intent, code);
    }

    /**
     * getBackPhone 设定头像，拍照或获取相册照片,返回的数据
     * 在 onActivityResult 中 调用
     *
     * @param context     上下文
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        回传Intent
     * @param imageView   要设置头像的imageview控件
     * @Override public void onActivityResult(int requestCode, int resultCode, Intent data)
     */
    public static File getBackPhone(final Context context, int requestCode, int resultCode, Intent data, ImageView imageView) {
        if (requestCode == 102 && resultCode == RESULT_OK) {
            //读取本地相册
            File file = readLocalImage(data, (Activity) context, imageView);
            return file;
        }
        if (requestCode == 103 && resultCode == RESULT_OK) {
            //获取拍照返回的数据
            File file = getPaizhaoData(data, imageView);
            return file;
        }
        return null;
    }

    /**
     * getPaizhaoData 获取拍照返回的数据
     *
     * @param data      回传Intent
     * @param imageView 要设置头像的imageview控件
     */
    private static File getPaizhaoData(Intent data, ImageView imageView) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        //显示图片
        imageView.setImageBitmap(bitmap);
        //保存图片路径
        try {
            File file = saveFile(bitmap);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * readLocalImage 读取本地相册
     *
     * @param activity  上下文
     * @param data      回传Intent
     * @param imageView 要设置头像的imageview控件
     */
    private static File readLocalImage(Intent data, Activity activity, ImageView imageView) {
        if (data == null) {
            return null;
        }
        Uri uri = data.getData();
        //转化成bitmap
        Bitmap bitmap = getBitmapFromUri(activity, uri);
        //显示图片
        imageView.setImageBitmap(bitmap);
        //保存图片路径
        try {
            File file = saveFile(bitmap);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getBitmapFromUri 将图片uri地址转成Bitmap
     *
     * @param activity 上下文
     * @param uri      意图
     */
    private static Bitmap getBitmapFromUri(Activity activity, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param bm
     * @throws IOException
     */
    public static File saveFile(Bitmap bm) throws IOException {
        String path = Environment.getExternalStorageDirectory().toString() + "/w65/icon_bitmap/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File myIconFile = new File(path + "myicon.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myIconFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myIconFile;
    }

    /**
     * 从本地获取图片
     *
     * @param pathString 文件路径
     * @return 图片
     */
    public static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        if (pathString == null) {
            return bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.id.iv_slid_home);
        }
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bitmap;
    }
}
