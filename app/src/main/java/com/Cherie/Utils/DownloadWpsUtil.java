package com.Cherie.Utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

/**
 * Created by liangxy on 2017/6/22.
 */
public class DownloadWpsUtil {
        //下载器
        private static DownloadManager downloadManager;
        //上下文
        private static Context mContext;
        //下载的ID
        private static long downloadId;
        public  DownloadWpsUtil(Context context){

            this.mContext = context;
        }

        //下载apk
        public static void downloadAPK(Context context,String url, String name) {
            mContext = context;
            //创建下载任务
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            //移动网络情况下是否允许漫游
            request.setAllowedOverRoaming(false);

            //在通知栏中显示，默认就是显示的
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setTitle("新版本Apk");
            request.setDescription("Apk Downloading");
            request.setVisibleInDownloadsUi(true);

            //设置下载的路径
            request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath() , name);

            //获取DownloadManager
            downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
            downloadId = downloadManager.enqueue(request);

            //注册广播接收者，监听下载状态
            mContext.registerReceiver(receiver,
                    new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        //广播监听下载的各个状态
        private static BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                checkStatus();
            }
        };


        //检查下载状态
        private static void checkStatus() {
            DownloadManager.Query query = new DownloadManager.Query();
            //通过下载的id查找
            query.setFilterById(downloadId);
            Cursor c = downloadManager.query(query);
            if (c.moveToFirst()) {
                int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                switch (status) {
                    //下载暂停
                    case DownloadManager.STATUS_PAUSED:
                        break;
                    //下载延迟
                    case DownloadManager.STATUS_PENDING:
                        break;
                    //正在下载
                    case DownloadManager.STATUS_RUNNING:
                        break;
                    //下载完成
                    case DownloadManager.STATUS_SUCCESSFUL:
                        //下载完成安装APK
                        installAPK();
                        break;
                    //下载失败
                    case DownloadManager.STATUS_FAILED:
                        Toast.makeText(mContext, "下载失败,请先到应用市场下次WPSOffice应用", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }

        //下载到本地后执行安装
        private static void installAPK() {
            //获取下载文件的Uri
            Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
            if (downloadFileUri != null) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        }


}
