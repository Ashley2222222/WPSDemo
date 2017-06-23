package com.Cherie.wpsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.Cherie.Adapter.FileListAdapter;
import com.Cherie.AsyncTask.OfficeFileScannerTask;
import com.Cherie.model.fileInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 文件列表页面，列表显示内存里的所有office文件
 * Created by liangxy on 2017/5/26.
 */
public class FileListActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.lv)
    ListView lv;
    FileListAdapter adapter;
    @Bind(R.id.btnConfirm)
    Button btnConfirm;
    @Bind(R.id.ll_confirm)
    LinearLayout llConfirm;
    static private List<fileInfo> fileInfos = new ArrayList<fileInfo>();
    static private List<fileInfo> fileChosenInfos = new ArrayList<fileInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_list);
        ButterKnife.bind(this);
        btnConfirm.setOnClickListener(this);
        llConfirm.setOnClickListener(this);
        initInfo();
    }

    private void initInfo() {
        Bundle bundle = getIntent().getExtras();
        fileChosenInfos  = (List<fileInfo>) bundle.getSerializable("fileChosenInfos");
        if(fileChosenInfos==null)
            fileChosenInfos = new ArrayList<fileInfo>();
        fileInfos = new ArrayList<fileInfo>();
        OfficeFileScannerTask scanTask = new OfficeFileScannerTask(fileInfos,fileChosenInfos,FileListActivity.this,  lv, adapter);
        scanTask.execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ll_confirm:
            case R.id.btnConfirm:
                getChosenFiles();
                break;
            default:
                break;
        }

    }

    /**
     * 返回选中的file
     * Created by liangxy on 2017/6/23 15:05
     */
    private void getChosenFiles() {
        fileChosenInfos=new ArrayList<>();
        for(fileInfo vi: fileInfos)
        {
            if(vi.getState()==1)
                fileChosenInfos.add(vi);
        }
        Intent intent = new Intent(FileListActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fileChosenInfos", (Serializable) fileChosenInfos);
        intent.putExtras(bundle);
        setResult(1, intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }

        return super.onKeyDown(keyCode, event);

    }
}
