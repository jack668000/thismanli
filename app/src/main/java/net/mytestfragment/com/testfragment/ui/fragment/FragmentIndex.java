package net.mytestfragment.com.testfragment.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lqr.recyclerview.LQRRecyclerView;
import com.yanzhenjie.sofia.NavigationView;
import com.yanzhenjie.sofia.StatusView;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.ui.activity.EvenBusTestActivity;
import net.mytestfragment.com.testfragment.ui.activity.IndexActivity;
import net.mytestfragment.com.testfragment.ui.base.BaseFragment;
import net.mytestfragment.com.testfragment.ui.model.MessageEvent;
import net.mytestfragment.com.testfragment.ui.presenter.IndexFgPresenter;
import net.mytestfragment.com.testfragment.ui.view.IndexFgView;
import net.mytestfragment.com.testfragment.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

public class FragmentIndex extends BaseFragment<IndexFgView,IndexFgPresenter> implements IndexFgView {

    TextView mHead,mFoot;
    View content;
    @Bind(R.id.rvContacts)
    LQRRecyclerView mRvContacts;
    @Bind(R.id.status_view)
    StatusView mStatusView;
    @Override
    public void initView(View rootView) {
        super.initView(rootView);

        mHead = new TextView(getContext());
        mFoot = new TextView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2Px(50));
        mHead.setLayoutParams(params);
        mFoot.setLayoutParams(params);
        mHead.setGravity(Gravity.CENTER);
        mFoot.setGravity(Gravity.CENTER);
        mHead.setText("header");
        mFoot.setText("footer");
        mHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EvenBusTestActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mStatusView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.back));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent){
        mHead.setText(messageEvent.getMessage());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.loadIndex();
    }

    @Override
    protected IndexFgPresenter createPresenter() {
        return new IndexFgPresenter((IndexActivity)getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_index;
    }

    @Override
    public void BackPress(int keyCode) {

    }


    @Override
    public View getHeaderView() {
        return mHead;
    }

    @Override
    public LQRRecyclerView getRvContacts() {
        return mRvContacts;
    }

    @Override
    public TextView getFooterView() {
        return mFoot;
    }
}
