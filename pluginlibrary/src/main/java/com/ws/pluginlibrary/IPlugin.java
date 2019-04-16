package com.ws.pluginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Creator :Wen
 * DataTime: 2019/4/15
 * Description:
 */
public interface IPlugin {

    int FROM_INTERNAl = 0;//从内部跳转
    int FROM_EXTERNAL = 1;//从外部跳转

    void onCreate(Bundle saveInstanceState);

    void attach(Activity proxyActivity);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode,int resultCode,Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestory();
}
