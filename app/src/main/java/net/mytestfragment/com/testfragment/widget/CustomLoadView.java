package net.mytestfragment.com.testfragment.widget;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.mytestfragment.com.testfragment.R;

public class CustomLoadView implements CustomLoadStates {

    private ViewGroup parentView;

    //文字内容
    private String[] des_content={"加载中","加载失败","加载完成"};
    private int des_cm = 0;
    //文字颜色
    private int des_color= R.color.back;
    //图片
    private int image_url = R.drawable.ic_state_loading;
    //背景
    private int back_color = R.color.f5;

    private Context mContext;

    private RelativeLayout parent;
    private ImageView image;
    private TextView descript;
    private LinearLayout liner_click;
    public View view;
    private RotateAnimation rotate;

    public CustomLoadView(Context mContext, ViewGroup mView) {
        this.mContext = mContext;
        this.parentView = mView;
    }
    private void initView(){
        if(view==null) {
            view = View.inflate(mContext, R.layout.custom_load_view, null);
            parent = view.findViewById(R.id.custom_parent);
            image = view.findViewById(R.id.image);
            descript = view.findViewById(R.id.descript);
            liner_click = view.findViewById(R.id.liner_click);
            liner_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(des_cm == 2){
                        loading();
                    }
                }
            });
            rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            LinearInterpolator lin = new LinearInterpolator();
            rotate.setInterpolator(lin);
            rotate.setDuration(2000);//设置动画持续周期
            rotate.setRepeatCount(-1);//设置重复次数
            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(10);//执行前的等待时间
//            ((Activity)mContext).setContentView(view);
        }

    }

    public CustomLoadView descriptColor(int des_color){
        this.des_color = des_color;
        return this;
    }

    public CustomLoadView image(int image) {
        this.image_url = image;
        return this;
    }

    public CustomLoadView background(int back_color) {
        this.back_color = back_color;
        return this;
    }

    public CustomLoadView show(){
        initView();
        parent.setBackgroundColor(mContext.getColor(back_color));
        image.setImageDrawable(mContext.getDrawable(image_url));
        descript.setText(des_content[des_cm]);
        descript.setTextColor(mContext.getColor(des_color));
        return this;
    }

    private void hide(){

    }

    @Override
    public void loading() {
        image.setAnimation(rotate);

        image_url = R.drawable.ic_state_loading;
        back_color = R.color.f5;
        des_cm = 0;
        show();
    }

    @Override
    public void loadFail() {
        image.clearAnimation();
        image_url = R.drawable.ic_state_error;
        back_color = R.color.colorAccent;
        des_cm = 1;
        show();
    }

    @Override
    public void loadSuccess() {
        image.clearAnimation();
        image_url = R.drawable.ic_state_login;
        back_color = R.color.hah;
        des_cm = 2;
        show();
        hide();
    }
}
