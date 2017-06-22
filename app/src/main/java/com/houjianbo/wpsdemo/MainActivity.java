package com.houjianbo.wpsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.houjianbo.Adapter.FileListAdapter;
import com.houjianbo.Adapter.ShowSelectFileListAdapter;
import com.houjianbo.model.fileInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    Context ctx=this;
    String stringPath;
    MyBroadCastReciver receiver;
    @Bind(R.id.openFlieList)
    Button openFlieList;
    @Bind(R.id.lv)
    ListView lv;
    ShowSelectFileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openFlieList = (Button) findViewById(R.id.openFlieList);


//        stringPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android多媒体开发高级编程.pdf";
//        System.out.println("StringPath:" + stringPath);
        openFlieList.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        FileListActivity.class);
                startActivityForResult(intent, 1);
//				boolean flag = openFile(stringPath);
//				if (flag == true) {
//
//					Toast.makeText(MainActivity.this," 打开文件成功", 2000).show();
//				}else{
//					Toast.makeText(MainActivity.this, "打开文件失败", 2000).show();
//				}


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("test", "onActivityResult() requestCode:" + requestCode
                + ",resultCode:" + resultCode + ",data:" + data);
        switch (requestCode) {

            case 1:

                if (null != data) {
                    //返回选择的文件名列表
                    Bundle bundle = data.getExtras();
                    List<fileInfo> fileChosenInfos = (List<fileInfo>) bundle.getSerializable("fileChosenInfos");
                    adapter = new ShowSelectFileListAdapter(ctx, fileChosenInfos);

                    adapter.notifyDataSetChanged();
                    lv.setAdapter(adapter);
                }
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //unregisterReceiver(receiver);

    }


}
