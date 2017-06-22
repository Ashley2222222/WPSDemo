/**
 *
 */
package com.houjianbo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.houjianbo.Utils.FileUtil;
import com.houjianbo.Utils.UIUtil;
import com.houjianbo.model.fileInfo;
import com.houjianbo.wpsdemo.R;

import java.io.File;
import java.util.List;

/**
 * @Fields FieldListAdapter : 选择文件返回后，显示文件列表
 *@author  liangxueyi
 * @since 2016/12/23 15:37
 */
public class ShowSelectFileListAdapter extends BaseAdapter {
	public static double FONTSIZE;
	private List<fileInfo> items = null;
	private Context context;

	public ShowSelectFileListAdapter(Context context, List<fileInfo> items) {
		this.context = context;
		this.items = items;
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

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		final ViewHolder viewHolder;
		LayoutInflater inflater = null;
		final int currPosition = position;
		if (convertView == null) {
			inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_file, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.thumb);
			viewHolder.cbState = (CheckBox) convertView.findViewById(R.id.cbState);
			viewHolder.cbState.setVisibility(View.GONE);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tv_name.setText(items.get(currPosition).getFileName()==null?"":items.get(currPosition).getFileName());
//		viewHolder.iv.setImageBitmap(items.get(currPosition).getIi().getBitmap());

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FileUtil.openFile(items.get(currPosition).getPath(),context);
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
