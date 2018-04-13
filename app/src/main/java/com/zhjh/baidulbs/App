package com.zhjh.baidulbs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;



public class App extends Application{

	public static Context mContext = null;
	public static String entityName = "";


	@Override
	public void onCreate() {
		super.onCreate();
    mContext = getApplicationContext();
    
    if ("com.baidu.track:remote".equals(CommonUtil.getCurProcessName(mContext))) {
			return;
		}
	

		SDKInitializer.initialize(mContext);
	
	}

	public static Context getContext() {
		return mContext;
	}

}
