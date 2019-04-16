package com.ws.pluginapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ws.pluginlibrary.PluginLibActivity;


public class PluginActivity extends PluginLibActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Toast.makeText(mProxyActivity,"tiaoz",Toast.LENGTH_SHORT).show();
    }

}
