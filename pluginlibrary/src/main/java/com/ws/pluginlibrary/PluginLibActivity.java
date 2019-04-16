package com.ws.pluginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Creator :Wen
 * DataTime: 2019/4/15
 * Description:
 */
public class PluginLibActivity extends Activity implements IPlugin {
    private int mFrom = FROM_INTERNAl;
    //插件的上下文
    public Activity mProxyActivity;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            mFrom = saveInstanceState.getInt("FROM");
        }
        if (mFrom == FROM_INTERNAl) {
            super.onCreate(saveInstanceState);
        }
    }

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivity= proxyActivity;
    }

    @Override
    public void setContentView(int layoutResID) {

        if (mFrom == FROM_INTERNAl) {
            super.setContentView(layoutResID);
        }else {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if (mFrom == FROM_INTERNAl) {
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if (mFrom == FROM_INTERNAl) {
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFrom == FROM_INTERNAl) {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAl) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == FROM_INTERNAl) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INTERNAl) {
            super.onStop();
        }
    }

    @Override
    public void onDestory() {
        if (mFrom == FROM_INTERNAl) {
            super.onRestart();
        }
    }
}
