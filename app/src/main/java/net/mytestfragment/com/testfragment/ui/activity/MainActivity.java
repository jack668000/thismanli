package net.mytestfragment.com.testfragment.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.app.MyApp;
import net.mytestfragment.com.testfragment.greendao.User;
import net.mytestfragment.com.testfragment.ui.base.BaseActivity;
import net.mytestfragment.com.testfragment.ui.presenter.MainPresenter;
import net.mytestfragment.com.testfragment.ui.view.MainView;

import java.util.List;
import java.util.Random;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainView,MainPresenter> {

    @Bind(R.id.bt1)
    Button bt1;
    @Bind(R.id.bt2)
    Button bt2;
    @Bind(R.id.bt3)
    Button bt3;
    @Bind(R.id.bt4)
    Button bt4;

    @Override
    public void init() {


    }

    @Override
    public void initListener() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random(10);
                User user = new User(null,"小米"+random.nextInt(10),10+random.nextInt(10));
                try{
                    MyApp.getInstances().getDaoSession().getUserDao().insert(user);
                }catch (Exception e){
                    Toask("插入数据库失败");
                }
                Toask("插入数据库成功");
                try{
                    List<User> userList = MyApp.getInstances().getDaoSession().getUserDao().loadAll();
                    for (User userbean :userList){
                        Toask(""+userbean.toString());
                    }

                }catch (Exception e){
                    Toask("查询数据库失败");
                }

                try{
                    MyApp.getInstances().getDaoSession().getUserDao().deleteAll();
                }catch (Exception e){
                    Toask("删除数据库失败");
                }
                    Toask("删除数据库成功");


              //  showWaitingDialog("Loading...");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WebTencentActivity.class ));
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WebOriginActivity.class ));
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),IndexActivity.class ));
            }
        });
    }

    @Override
    protected boolean isToolbarCanBack() {
        return false;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
}
