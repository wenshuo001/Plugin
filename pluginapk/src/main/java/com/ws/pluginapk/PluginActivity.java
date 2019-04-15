package com.ws.pluginapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PluginActivity extends com.ws.pluginlibrary.PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Toast.makeText(this,"跳过来拉",Toast.LENGTH_SHORT).show();
    }
}
