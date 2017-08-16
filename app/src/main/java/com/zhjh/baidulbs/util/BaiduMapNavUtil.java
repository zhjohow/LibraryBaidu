package com.zhjh.baidulbs.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;


public class BaiduMapNavUtil {  
    private static BaiduMapNavUtil instance = null;  
    private String BaiduPackage = "com.baidu.BaiduMap";//百度地图  
    private String sourceApplication = "clevel";  
    private static Context context;  
      
    public static BaiduMapNavUtil getInstance(Context con){
        if(instance == null){  
            context = con;  
            instance = new BaiduMapNavUtil();
        }  
        return instance;  
    }  
      
    //发起百度驾车导航  
    public void goToBaiduNavi(Context context,double lat,double lng){
        Intent intent = new Intent();  
        String location = lat+","+lng;  
        intent.setData(Uri.parse("baidumap://map/navi?location="+location));  
        intent.setPackage(BaiduPackage);  
        context.startActivity(intent);  
    }  
    
        //发起百度地址解析  
    public void goToBaiduGeocoder(Context context,double lat,double lng){
        Intent intent = new Intent();  
        String location = lat+","+lng;  
        intent.setData(Uri.parse("baidumap://map/geocoder?location="+location));  
        intent.setPackage(BaiduPackage);  
        context.startActivity(intent);  
    }  
      
    /**  
     * 检查手机上是否安装了指定的软件  
     * @param flag：1百度 
     * @return  
     */    
    private boolean isAvilibleMap(int flag){     
        //获取packagemanager     
        final PackageManager packageManager = context.getPackageManager();    
        //获取所有已安装程序的包信息     
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);    
        //用于存储所有已安装程序的包名     
        List<String> packageNames = new ArrayList<String>();    
        //从pinfo中将包名字逐一取出，压入pName list中     
        if(packageInfos != null){     
            for(int i = 0; i < packageInfos.size(); i++){     
                String packName = packageInfos.get(i).packageName;     
                packageNames.add(packName);     
            }     
        }  
          
        boolean st = false;  
        if(flag == 1){  
            st = packageNames.contains(BaiduPackage);    
        }
        return st;  
   }   

}  
