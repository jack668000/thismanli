package net.mytestfragment.com.testfragment.ui.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRHeaderAndFooterAdapter;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.adapter.OnItemClickListener;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.ui.base.BaseActivity;
import net.mytestfragment.com.testfragment.ui.base.BasePresenter;
import net.mytestfragment.com.testfragment.ui.bean.IndexBean;
import net.mytestfragment.com.testfragment.ui.view.IndexFgView;

import java.util.ArrayList;
import java.util.List;


public class IndexFgPresenter extends BasePresenter<IndexFgView> {
    private List<IndexBean> mData = new ArrayList<>();
    private LQRHeaderAndFooterAdapter mAdapter;

    public IndexFgPresenter(BaseActivity context) {
        super(context);
    }
    public void loadIndex(){
        setAdapter();
        loadData();
    }
    private void setAdapter(){

        if(mAdapter==null){
            LQRAdapterForRecyclerView adapter = new LQRAdapterForRecyclerView<IndexBean>(mContext,mData,R.layout.item_index) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, IndexBean item, int position) {
                    helper.setText(R.id.text,item.getName());
                }
            };
            adapter.addHeaderView(getView().getHeaderView());
            adapter.addFooterView(getView().getFooterView());
            mAdapter = adapter.getHeaderAndFooterAdapter();
            getView().getRvContacts().setAdapter(mAdapter);
        }
        ((LQRAdapterForRecyclerView) mAdapter.getInnerAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                Toast.makeText(mContext,"点击了 "+mData.get(position-1).getName(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void loadData(){

        mData.clear();
        for (int i = 0; i < 20; i++) {
            IndexBean bean = new IndexBean();
            bean.setId(""+i);
            bean.setName("我是test     "+i);
            mData.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }
}
