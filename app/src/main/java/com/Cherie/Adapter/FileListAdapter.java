/**
 *
 */
package com.Cherie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Cherie.Utils.FileUtil;
import com.Cherie.Utils.UIUtil;
import com.Cherie.model.*;
import com.Cherie.wpsdemo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author liangxueyi
 * @Fields FieldListAdapter : 场所列表适配器
 * @since 2016/12/23 15:37
 */
public class FileListAdapter extends BaseAdapter {
    public static double FONTSIZE;
    private List<fileInfo> items = null;
    private Context context;
    List<Integer> checkPosition;

    public FileListAdapter(Context context, List<fileInfo> items) {
        this.context = context;
        this.items = items;
        init();
        checkPosition = new ArrayList<Integer>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int index) {
        return items.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    public static HashMap<Integer, Boolean> isSelected;

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < items.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = null;
        final int currPosition = position;
        viewHolder = new ViewHolder();
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_file, null);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        viewHolder.iv = (ImageView) convertView.findViewById(R.id.thumb);
        viewHolder.cbState = (CheckBox) convertView.findViewById(R.id.cbState);
        //定义为final解决list项多过1屏时，点击一条项目时其他屏的相应位置项目被选中问题
        final CheckBox finalholder = viewHolder.cbState;
        viewHolder.cbState.setTag(new Integer(position));
        if (checkPosition != null) {
            finalholder.setChecked((checkPosition.contains(new Integer(position)) ? true : false));
        } else {
            finalholder.setChecked(false);
        }

        finalholder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {//合格
                    if (!checkPosition.contains(finalholder.getTag())) {
                        checkPosition.add((Integer) finalholder.getTag());
                    }

                    buttonView.setButtonDrawable(R.drawable.check_on);
                    items.get(currPosition).setState(1);
                } else {
                    buttonView.setButtonDrawable(R.drawable.check);
                    items.get(currPosition).setState(0);

                    if (checkPosition.contains(finalholder.getTag())) {
                        checkPosition.remove(finalholder.getTag());
                    }
                }
                Log.i("FileListAdapterCheckBox", currPosition + "");
            }
        });


        convertView.setTag(viewHolder);


        viewHolder.tv_name.setText(items.get(currPosition).getFileName() == null ? "" : items.get(currPosition).getFileName());
//		viewHolder.iv.setImageBitmap(items.get(currPosition).getIi().getBitmap());
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed = false;
                installed = FileUtil.isInstall(context);
                if (installed)
                    FileUtil.openFile(items.get(currPosition).getPath(), context);
                else {
                    FileUtil.installWps(context);
                }
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.setCheckBox(finalholder);
                Log.i("FileListAdapter", currPosition + "");
            }
        });


        this.notifyDataSetChanged();
        return convertView;
    }

    final class ViewHolder {
        private TextView tv_name;
        private ImageView iv;
        private CheckBox cbState;
    }

}
