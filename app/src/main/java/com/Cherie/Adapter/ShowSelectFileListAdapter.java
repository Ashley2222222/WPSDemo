/**
 *
 */
package com.Cherie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Cherie.Utils.FileUtil;
import com.Cherie.Utils.UIUtil;
import com.Cherie.model.fileInfo;
import com.Cherie.wpsdemo.R;

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
			convertView = inflater.inflate(R.layout.item_file_chosen, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.thumb);
			viewHolder.delBtn = (ImageButton) convertView.findViewById(R.id.delBtn);
			viewHolder.right_side = (RelativeLayout) convertView.findViewById(R.id.right_side);
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

		viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				boolean installed = false;
				installed = FileUtil.isInstall(context);
				if(installed)
					FileUtil.openFile(items.get(currPosition).getPath(),context);
				else{
					FileUtil.installWps(context);
				}
			}
		});
		viewHolder.right_side.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewHolder.tv_name.performClick();
			}
		});
		//删除已选项目
		viewHolder.delBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				items.remove(currPosition);
				notifyDataSetChanged();
			}
		});
		this.notifyDataSetChanged();
		return convertView;
	}
	final class ViewHolder {
		private RelativeLayout right_side;
		private TextView tv_name;
		private ImageView iv;
		private ImageButton delBtn;
	}

}
