package com.ws.plugin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ws.pluginlibrary.PluginManager;
import com.ws.pluginlibrary.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);

        findViewById(R.id.jiazai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path= Utils.copyAssetAndWrite(MainActivity.this,"plug.apk");
                //加载apk
                PluginManager.getInstance().loadApk(path);
            }
        });

        findViewById(R.id.tiaozhuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //指定跳转的类名
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.ws.pluginapk.PluginActivity");
                startActivity(intent);
            }
        });
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("MainActivity", "checkPermission: 已经授权！");
        }
    }

}
