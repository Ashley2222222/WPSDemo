package com.houjianbo.Utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;


/**
 * @Description 用于处理和文件存储相关的工具类
 * @Author LXY
 */
public class StorageUtils {

    private static Context context = null;

    public static String path = Environment.getExternalStorageDirectory().getPath() + "/fileTest/";

    /**
     * 用于判断该设备是否装有SD卡
     *
     * @param context 上下文对象
     * @return 判断结果
     */
    public static boolean isSDCardMounted(Context context) {
        boolean isSDCardMounted = false;
        return isSDCardMounted;
    }


    /**
     * 获取SD卡的根目录
     *
     * @param context 上下文对象
     * @return SD卡的根目录
     */
    public static String getRootPath(Context context) {

        //判断SDcard是否存在并且可读写
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return path;
        } else {
            Toast.makeText(context, "该设备没有插入SD卡~", Toast.LENGTH_SHORT).show();
            return null;
        }

    }


    /**
     * 名称：初始化APP时生成对应的存储空间
     * Copyright  jeisai All right reserved.
     *
     * @return boolean    返回类型
     * @throws
     * @Title: initApp
     * @author liuxuhao
     * @since 2015-8-4 下午2:23:07
     */
    public static boolean initApp() {
        String filePath = getRootPath(context) ;
        if (filePath == null) {
            return false;
        } else {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
            return true;
        }

    }

//
//    public static boolean isFileExist(String fileName, String path) {
//        File file = new File(path + fileName);
//        file.isFile();
//        return file.exists();
//    }
//
    //删除文件
    public static void delFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }
//删除路径下所有文件
//    public static void deleteDir(String path) {
//        File dir = new File(path);
//        if (dir == null || !dir.exists() || !dir.isDirectory())
//            return;
//
//        for (File file : dir.listFiles()) {
//            if (file.isFile())
//                file.delete();
//            else if (file.isDirectory())
//                deleteDir(path);
//        }
//        dir.delete();
//    }
//
//    public static boolean fileIsExists(String path) {
//        try {
//            File f = new File(path);
//            if (!f.exists()) {
//                return false;
//            }
//        } catch (Exception e) {
//
//            return false;
//        }
//        return true;
//    }
//
//    public static String getVideoName() {
//        String pathDetail = "";
//        // 设置视频文件输出的路径
//        pathDetail =path+ "/videoTest/"+ System.currentTimeMillis() + ".mp4";
//
//        return pathDetail;
//    }

}
