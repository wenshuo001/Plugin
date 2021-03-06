package com.ws.pluginlibrary;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Creator :Wen
 * DataTime: 2019/4/15
 * Description:
 */
public class ProxyActivity extends Activity {
    private String mClassName;
    private PluginApk mPluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (mPluginApk == null){
            Log.e("加载apk错误","错误");
        }
        try {
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
            //实例化Activiy 注意这里的Activity是没有生命周期，也没有上下文环境
            Object object= clazz.newInstance();
            if (object instanceof IPlugin){
                mIPlugin = (IPlugin) object;
                mIPlugin.attach(this);
                Bundle bundle =new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk!=null ? mPluginApk.mResources:super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk!=null ? mPluginApk.mAssetManager:super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk!=null ? mPluginApk.mDexClassLoader:super.getClassLoader();
    }
}
