package net.mytestfragment.com.testfragment.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.ui.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EvenBusTestActivity extends AppCompatActivity {
    @Bind(R.id.but)
    Button mButton;
    @Bind(R.id.but1)
    Button mButton1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbustest);
        ButterKnife.bind(this);
        jumpActivity();
    }
    private void jumpActivity() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("奇怪的头部"));
                finish();
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(
                        "com.meiji.toutiao",  //91通讯录包名
                        "com.meiji.toutiao.SplashActivity_circle"); //91通讯录的Activity名字


                Intent intent = new Intent();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.setComponent(componentName);

                intent.setAction("");
                startActivity(intent);
            }
        });
    }

}
