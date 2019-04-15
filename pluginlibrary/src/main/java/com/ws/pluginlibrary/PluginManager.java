package com.ws.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Creator :Wen
 * DataTime: 2019/4/15
 * Description:
 */
public class PluginManager {

    private static final PluginManager instance = new PluginManager();

    private PluginManager(){

    }

    public static PluginManager getInstance(){
        return instance;
    }


    private PluginApk mPluginApk;
    private Context context;

    public void init(Context context){
        this.context=context;
    }

    //加载插件apk

    public void loadApk(String apkPath){
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }
        DexClassLoader classLoader =createDexClassLoader(apkPath);
        AssetManager am=createAssetManager(apkPath);
        Resources resources= createResources(am);
        mPluginApk = new PluginApk(packageInfo,classLoader,resources);
    }

    public PluginApk getPluginApk(){
        return mPluginApk;
    }


    //创建访问插件apk的DexClassLoader
    public DexClassLoader createDexClassLoader(String apkPath){
        File file = context.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,context.getClassLoader());
    }
    //创建访问插件apk资源的Aseetmanager对象
    public AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am= AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //创建访问插件apk资源的Resource对象
    public Resources createResources(AssetManager am){
        Resources res= context.getResources();
        return new Resources(am,res.getDisplayMetrics(),res.getConfiguration());
    }
}
