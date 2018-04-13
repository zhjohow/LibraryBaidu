package com.zhjh.baidulbs.service;

import android.util.Log;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.model.LocationMode;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.ProtocolType;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.StatusCodes;



/**
 * baidu鹰眼
 *
 */

public class TraceService {

        private AtomicInteger mSequenceGenerator = new AtomicInteger();
	
	/*轨迹服务ID*/
	public static long serviceId = ;

	/*设备标识*/
	private String entityName = "";

	/*是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。*/
	private static boolean isNeedObjectStorage = false;
	// 定位周期(单位:秒)
	public static int gatherInterval = 5;
	// 打包回传周期(单位:秒)
	public static int packInterval = 10;

	public static LBSTraceClient mTraceClient;
	public static Trace mTrace;
	private static TraceListener mListener;
	
		/**
	 * 服务是否开启标识
	 */
	public static boolean isTraceStarted = false;

	/**
	 * 采集是否开启标识
	 */
	public static boolean isGatherStarted = false;

	/**
	 * 初始化轨迹客户端
	 */
	public static LBSTraceClient getTraceClient() {
		if (null == mTraceClient) {
			synchronized (TraceService.class) {
				if (null == mTraceClient) {
					mTraceClient = new LBSTraceClient(App.getContext());

					mTraceClient.setProtocolType(ProtocolType.HTTP);
					//高精度gps
					mTraceClient.setLocationMode(LocationMode.High_Accuracy);
					// 设置定位和打包周期
					mTraceClient.setInterval(gatherInterval, packInterval);
				}
			}
		}
		return mTraceClient;
	}

	/**
	 * 初始化鹰眼服务
	 */
	public static Trace getTrace(String entityName) {
	
		if (null == mTrace) {
			synchronized (TraceService.class) {
				if (null == mTrace) {
					mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
				}
			}
		}
		Log.e("tag", "mtrace=" + mTrace);
		return mTrace;
	}

	/**
	 * 开启鹰眼服务
	 */
	public static void onStartTrace() {
	    if (mTraceClient != null) {
	    
		  mTraceClient.startTrace(mTrace, mOnTraceListener);
		
		}
		
	}
	
	public static void onStartGather() {
		if (mTraceClient != null) {

	           mTraceClient.startGather(traceListener);

		}


	}

	/**
	 * 关闭采集信息和鹰眼服务
	 */
	public static void clearTrace() {
		if (mTraceClient != null) {
		
		  mTraceClient.stopGather(mOnTraceListener);
		
	
		}
	}
	
	public void stopTrace() {

	        if (isTraceStarted
				&& isTraceStarted) {
			// 退出app停止轨迹服务时，不再接收回调，将OnTraceListener置空
			if (mClient != null) {
				mClient.setOnTraceListener(null);
				mClient.stopTrace(mTrace, null);
				mClient.clear();
			}
		} else {
			if (mClient != null) {
				mClient.clear();
			}
		}
		isTraceStarted = false;
		isGatherStarted = false;

	}


	/**
	 * 开启服务的监听
	 */
	public static OnTraceListener mOnTraceListener = new OnTraceListener() {
		@Override
		public void onBindServiceCallback(int i, String s) {
			//Log.e("tag", "绑定服务成功");
	
		}

		@Override
		public void onStartTraceCallback(int i, String s) {
			/**
			 * 0：成功
			 10000：请求发送失败
			 10001：服务开启失败
			 10002：参数错误
			 10003：网络连接失败
			 10004：网络未开启
			 10005：服务正在开启
			 10006：服务已开启
			 10007：服务正在停止
			 */

			if (i >= StatusCodes.START_TRACE_REQUEST_FAILED && i <= StatusCodes.START_TRACE_NETWORK_CLOSED) {
				//Log.e("tag","网络可能出现异常");
				s = "网络可能出现异常";
			} else if (i == 10005) {
				//Log.e("tag","服务开启中");
				//mTraceClient.startGather(mOnTraceListener);
			} else if (i == 0) {
				//Log.e("tag","服务开启成功");
				s = "服务开启成功";
			}


		}

		@Override
		public void onStopTraceCallback(int i, String s) {
		
		}

		@Override
		public void onStartGatherCallback(int i, String s) {
			/**
			 * 0：成功
			 12000：请求发送失败
			 12001：采集开启失败
			 12002：服务未开启
			 12003：采集已开启
			 */

		}

		@Override
		public void onStopGatherCallback(int i, String s) {

		}

		@Override
		public void onPushCallback(byte b, PushMessage message) {

		}

		@Override
		public void onInitBOSCallback(int i, String s) {
		
		}
	};
	
		/**
	 * 初始化请求公共参数
	 *
	 * @param request
	 */
	public void initRequest(BaseRequest request) {
		if (request != null){
			request.setTag(getTag());
			request.setServiceId(serviceId);
		}

	}

	/**
	 * 获取请求标识
	 *
	 * @return
	 */
	public int getTag() {
		return mSequenceGenerator.incrementAndGet();
	}
}
