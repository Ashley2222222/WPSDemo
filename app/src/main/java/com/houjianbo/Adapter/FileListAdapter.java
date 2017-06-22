/**
 *
 */
package com.houjianbo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
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
import com.houjianbo.model.*;
import com.houjianbo.wpsdemo.R;

import java.io.File;
import java.util.List;

/**
 * @Fields FieldListAdapter : 场所列表适配器
 *@author  liangxueyi
 * @since 2016/12/23 15:37
 */
public class FileListAdapter extends BaseAdapter {
	// 下载成功
	public static final int DOWNLOAD_ERROR = 7;
	// 下载失败
	public static final int DOWNLOAD_SUCCESS = 6;
	public static double FONTSIZE;
	private List<fileInfo> items = null;
	private Context context;

	public FileListAdapter(Context context, List<fileInfo> items) {
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

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.cbState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {//合格
						buttonView.setButtonDrawable(R.drawable.check_on);
						items.get(currPosition).setState(1);
					}else{
						buttonView.setButtonDrawable(R.drawable.check);
						items.get(currPosition).setState(0);
					}

			}});
		viewHolder.tv_name.setText(items.get(currPosition).getFileName()==null?"":items.get(currPosition).getFileName());
//		viewHolder.iv.setImageBitmap(items.get(currPosition).getIi().getBitmap());

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				boolean installed = false;
				installed = FileUtil.isInstall(context);
				if(installed)
				FileUtil.openFile(items.get(currPosition).getPath(),context);
				else{
					FileUtil.installWps(handler,context);
				}
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
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case DOWNLOAD_SUCCESS:
					// 下载成功
					File file = (File) msg.obj;
					Intent intent = new Intent();
					intent.setAction("android.intent.action.VIEW");
					intent.addCategory("android.intent.category.DEFAULT");
					intent.setDataAndType(Uri.fromFile(file),
							"application/vnd.android.package-archive");
					context.startActivity(intent);
					break;
				case DOWNLOAD_ERROR:
					// 下载失败
					Toast.makeText(context,"下载失败",Toast.LENGTH_LONG).show();
					break;
				default:
					break;
			}
		}

	};
}
