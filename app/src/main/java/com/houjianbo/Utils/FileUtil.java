package com.houjianbo.Utils;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.houjianbo.CommVariable;
import com.houjianbo.model.WpsInfo;

import java.io.File;
import java.util.List;

/**
 * Created by liangxy on 2017/6/22.
 */
public class FileUtil {
    // 下载成功
    public static final int DOWNLOAD_ERROR = 7;
    // 下载失败
    public static final int DOWNLOAD_SUCCESS = 6;

    private static ProgressDialog mProgressDialog;
    public static boolean openFile(String path, Context ctx) {

        mProgressDialog = new ProgressDialog(ctx);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(WpsInfo.OPEN_MODE, WpsInfo.OpenMode.NORMAL); // 打开模式
        bundle.putBoolean(WpsInfo.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
        bundle.putString(WpsInfo.THIRD_PACKAGE, ctx.getPackageName()); // 第三方应用的包名，用于对改应用合法性的验证
        bundle.putBoolean(WpsInfo.CLEAR_BUFFER, true);// 清除打开记录
        bundle.putBoolean(WpsInfo.CLEAR_TRACE, true);// 清除打开记录
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName(WpsInfo.PackageName.NORMAL, WpsInfo.ClassName.NORMAL);

        File file = new File(path);
        if (file == null || !file.exists()) {
            System.out.println("文件为空或者不存在");
            Log.i("file", "文件为空或者不存在");
            return false;
        }

        if (CommVariable.IS_ONLINE) {
            //在支持http模式打开文档的版本下
            Log.i("online", "在线读取");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            String uriString ="http://119.23.20.192/eastnet_wechat/test.doc" ;
            Uri uri = Uri.parse(uriString);
            intent.setData(uri);
            try {
                ctx.startActivity(intent);
            } catch (ActivityNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            intent.putExtras(bundle);
            try {
                ctx.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                String uriString ="http://119.23.20.192/eastnet_wechat/test.doc" ;
                uri = Uri.parse(uriString);
                intent.setData(uri);
                try {
                    ctx.startActivity(intent);
                } catch (ActivityNotFoundException e1) {
                    e1.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    //安装wps
    public static void installWps(Handler handler,Context ctx) {
        mProgressDialog = new ProgressDialog(ctx);
        DownloadWpsUtil downloadUtils =   new DownloadWpsUtil(ctx);
        downloadUtils.downloadAPK(ctx,"http://dl.op.wpscdn.cn/dl/wps/mobile/apk/moffice_10.3.2_1033_cn00563_multidex_9119546.apk","office.apk");
    }


    //判断是否已安装wps
    public static boolean  isInstall(Context ctx) {
        boolean isInstall = false;
        List<PackageInfo> list =ctx.getPackageManager().getInstalledPackages(
                PackageManager.GET_PERMISSIONS);

        StringBuilder stringBuilder = new StringBuilder();

        for (PackageInfo packageInfo : list) {
            stringBuilder.append("package name:" + packageInfo.packageName
                    + "\n");
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            stringBuilder.append("应用名称:"
                    + applicationInfo.loadLabel(ctx.getPackageManager()) + "\n");
            if(packageInfo.packageName.equals("cn.wps.moffice_eng"))
                isInstall = true;
            if (packageInfo.permissions != null) {

                for (PermissionInfo p : packageInfo.permissions) {
                    stringBuilder.append("权限包括:" + p.name + "\n");
                }}}

        return  isInstall;
    }
}
