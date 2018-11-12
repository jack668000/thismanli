package net.mytestfragment.com.testfragment.ui.view;

import android.view.View;
import android.widget.TextView;

import com.lqr.recyclerview.LQRRecyclerView;

public interface IndexFgView {
    View getHeaderView();

    LQRRecyclerView getRvContacts();

    TextView getFooterView();
}
