package com.houjianbo.wpsdemo;

import com.houjianbo.model.WpsInfo.Reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadCastReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		switch (intent.getAction()) {
		case Reciver.ACTION_BACK://返回键广播
			System.out.println(Reciver.ACTION_BACK);

			break;
		case Reciver.ACTION_CLOSE://关闭文件时候的广播
			System.out.println(Reciver.ACTION_CLOSE);
		
			break;
		case Reciver.ACTION_HOME://home键广播
			System.out.println(Reciver.ACTION_HOME);

			break;
		case Reciver.ACTION_SAVE://保存广播
			System.out.println(Reciver.ACTION_SAVE);

			break;

		default:
			break;
		}

	}

}
