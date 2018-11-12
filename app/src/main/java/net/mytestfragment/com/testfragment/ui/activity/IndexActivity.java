package net.mytestfragment.com.testfragment.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.yanzhenjie.sofia.Sofia;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.ui.base.BaseActivity;
import net.mytestfragment.com.testfragment.ui.base.BasePresenter;
import net.mytestfragment.com.testfragment.ui.fragment.FragmentContent;
import net.mytestfragment.com.testfragment.ui.fragment.FragmentFactory;
import net.mytestfragment.com.testfragment.ui.fragment.FragmentIndex;
import net.mytestfragment.com.testfragment.ui.fragment.FragmentMessage;
import net.mytestfragment.com.testfragment.ui.presenter.IndexPresenter;
import net.mytestfragment.com.testfragment.ui.view.IndexView;

import butterknife.Bind;

public class IndexActivity extends BaseActivity<IndexView,IndexPresenter> {

    private static final String POSITION = "position";
    private int pos = 0;

    @Bind(R.id.navigation)
    BottomNavigationView navigation;

    private FragmentIndex fragmentIndex;
    private FragmentContent fragmentContent;
    private FragmentMessage fragmentMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            fragmentIndex =FragmentFactory.getInstance().getIndexFragment();
            fragmentContent =FragmentFactory.getInstance().getContentpFragment();
            fragmentMessage =FragmentFactory.getInstance().getMessageFragment();
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
        } else {
            showFragment(pos);
        }

        // 不实用Sofia设置Status颜色，因为不会随着Fragment的改变而变化，在Fragment中自行设置。
        Sofia.with(this)
                .invasionStatusBar()
                .statusBarBackground(Color.TRANSPARENT);
    }

    @Override
    public void initData() {
        super.initData();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int Selectedpo = 0;
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        Selectedpo = 0;
                        break;
                    case R.id.navigation_dashboard:
                        Selectedpo = 1;
                        break;
                    case R.id.navigation_notifications:
                        Selectedpo = 2;
                        break;
                }
                if(pos!=Selectedpo) {
                    pos=Selectedpo;
                    showFragment(pos);
                }
                return true;
            }
        });


    }



    @Override
    protected IndexPresenter createPresenter() {
        return new IndexPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_index;
    }


    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            /**
             * 如果Fragment为空，就新建一个实例
             * 如果不为空，就将它从栈中显示出来
             */
            case 0:
                if (fragmentIndex == null) {
                    fragmentIndex = FragmentFactory.getInstance().getIndexFragment();
                    ft.add(R.id.frameLayout, fragmentIndex, FragmentIndex.class.getName());
                } else {
                    ft.show(fragmentIndex);
                }
                break;

            case 1:
                if (fragmentContent == null) {
                    fragmentContent = FragmentFactory.getInstance().getContentpFragment();
                    ft.add(R.id.frameLayout, fragmentContent, FragmentContent.class.getName());
                } else {
                    ft.show(fragmentContent);
                }
                break;
            case 2:
                if (fragmentMessage == null) {
                    fragmentMessage = FragmentFactory.getInstance().getMessageFragment();
                    ft.add(R.id.frameLayout, fragmentMessage, FragmentMessage.class.getName());
                } else {
                    ft.show(fragmentMessage);
                }
                break;
        }

        ft.commit();
    }
    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (fragmentIndex != null) {
            ft.hide(fragmentIndex);
        }
        if (fragmentMessage != null) {
            ft.hide(fragmentMessage);
        }
        if (fragmentContent != null) {
            ft.hide(fragmentContent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(fragmentContent!=null&&!fragmentContent.isHidden()) {
            fragmentContent.BackPress(keyCode);
        }else{
            finish();
        }
        return true;
    }
}
