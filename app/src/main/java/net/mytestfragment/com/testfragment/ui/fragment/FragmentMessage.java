package net.mytestfragment.com.testfragment.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.mytestfragment.com.testfragment.widget.CustomLoadView;

public class FragmentMessage extends Fragment {
    private CustomLoadView customLoadView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    Runnable runnable1 = new Runnable(){

        public void run() {

            switch (times){
                case 1:
                    customLoadView.loading();
                    break;
                case 2:
                    customLoadView.loadFail();
                    break;
                case 3:
                    customLoadView.loadSuccess();
                    break;
            }
            times++;
            if(times>3){
                times = 1;
            }
            delay();



        }

    };
    private int times = 1;
    private void delay(){

        new Handler().postDelayed(runnable1, 3500);
    }
    private void delay(int delay){

        new Handler().postDelayed(runnable1, delay);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        customLoadView = new CustomLoadView(getContext(),null);
        customLoadView.show();
        delay(0);
        return customLoadView.view;
    }
}
