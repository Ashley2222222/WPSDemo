package com.Cherie.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import com.Cherie.Adapter.FileListAdapter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.Cherie.model.fileInfo;

/**
 * 遍历列出所有视频文件
 * Created by liangxy on 2017/5/26.
 */
public class OfficeFileScannerTask extends AsyncTask<Void, Integer, List<fileInfo>> {
    private List<fileInfo> fileInfos = new ArrayList<fileInfo>();
    List<fileInfo> fileChosenInfos = new ArrayList<fileInfo>();
    private Activity context = null;
    private ListView lv;
    private FileListAdapter adapter = null;

    public OfficeFileScannerTask( List<fileInfo> fileInfos, List<fileInfo> fileChosenInfos, Activity context, ListView lv, FileListAdapter adapter) {
        this.fileInfos = fileInfos;
        this.fileChosenInfos = fileChosenInfos;
        this.context = context;
        this.lv = lv;
        this.adapter = adapter;
    }

    @Override
    protected List<fileInfo> doInBackground(Void... params) {
        fileInfos = getVideoFile(fileInfos, Environment.getExternalStorageDirectory());
        fileInfos = filterVideo(fileInfos);
        Log.i("ScannerTask", "office文件数：" + fileInfos.size());
        return fileInfos;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<fileInfo> fileInfos) {
        super.onPostExecute(fileInfos);
        adapter = new FileListAdapter(context, fileInfos,fileChosenInfos);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    /**
     * 获取视频文件
     *
     * @param list
     * @param file
     * @return
     */
    private List<fileInfo> getVideoFile(final List<fileInfo> list, File file) {

        file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {

                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".ppt")
                            || name.equalsIgnoreCase(".pptx")
                            || name.equalsIgnoreCase(".xls")
                            || name.equalsIgnoreCase(".xlsx")
                            || name.equalsIgnoreCase(".doc")
                            || name.equalsIgnoreCase(".docx")
                            || name.equalsIgnoreCase(".txt")
                            ) {
                        fileInfo video = new fileInfo();
                        file.getUsableSpace();
                        video.setFileName(file.getName());
                        video.setPath(file.getAbsolutePath());
                        video.setState(0);//默认未选中

                        Log.i("ScannerTask", "name" + video.getPath());
                        list.add(video);
                        return true;
                    }
                    //判断是不是目录
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });

        return list;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileInfos
     * @return
     */
    private List<fileInfo> filterVideo(List<fileInfo> fileInfos) {
        List<fileInfo> newVideos = new ArrayList<fileInfo>();
        for (fileInfo videoInfo : fileInfos) {
            File f = new File(videoInfo.getPath());
            if (f.exists() && f.isFile()) {
                newVideos.add(videoInfo);
                Log.i("ScannerTask", "文件大小" + f.length());
            } else {
                Log.i("ScannerTask", "文件不存在");
            }
        }
        return newVideos;
    }

}
